package motogp.controller.motorsportstats;

import motogp.controller.motogpdata.MotoGPOnlineData;
import motogp.model.*;

import motogp.utils.JsonReader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MotorsportOnlineData implements MotoGPOnlineData {

	private static final String URL = "https://results.motorsportstats.com/";
	private static final String URL_JSON_SESSIONS = "https://mssproxy.motorsportstats.com/web/3.0.0/sessions/";
	private static final String URL_JSON_SEASONS = "https://mssproxy.motorsportstats.com/web/3.0.0/seasons/";

	private RaceCode raceCode= null;
	private int raceNumber = -1;


	/**
	 * If the results are not available or the selected session does not exist, this method will return an empty list.
	 * @param category
	 * @param year
	 * @param code
	 * @param session
	 * @return
	 */
	@Override
	public List<RiderOnlineData> getResultsByRaceCode(Category category, int year, RaceCode code, Session session) {
		List<RiderOnlineData> result = new ArrayList<>();

		try {
			// Requests the JSON object from the website
			raceCode = code;
			JSONObject jsonObject = getJsonObjectResults(category, year, session);

			// Converts the JSON object into a list of RiderOnlineData
			JSONArray gridJSON = jsonObject.getJSONArray("details");
			result = getRiderList(gridJSON);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			// Reset of the values before returning the results
			raceCode = null;
			raceNumber = -1;
			return result;
		}
	}

	/**
	 * If the results are not available or the selected session does not exist, this method will return an empty list.
	 * @param category
	 * @param year
	 * @param raceNumber
	 * @param session
	 * @return
	 */
	@Override
	public List<RiderOnlineData> getResultsByRaceNumber(Category category, int year, int raceNumber, Session session) {
		List<RiderOnlineData> result = new ArrayList<>();

		try {
			// Requests the JSON object from the website
			this.raceNumber = raceNumber;
			JSONObject jsonObject = getJsonObjectResults(category, year, session);

			// Converts the JSON object into a list of RiderOnlineData
			JSONArray gridJSON = jsonObject.getJSONArray("details");
			result = getRiderList(gridJSON);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			// Reset of the values before returning the results
			raceCode = null;
			this.raceNumber = -1;
			return result;
		}
	}

	/**
	 * If the standings are not available, an empty List will be returned.
	 * @param category
	 * @param year
	 * @return
	 */
	@Override
	public List<RiderStandingsData> getChampionshipStandings(Category category, int year) {
		List<RiderStandingsData> standings = new ArrayList<>();

		try{
			// Requests the JSONArray from the website
			JSONArray jsonArray = getJsonArrayStandings(category, year);

			// Converts the JSONArray into a list of RiderStandingsData
			standings = getRiderStandingsList(jsonArray);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			return standings;
		}

	}

	private List<RiderStandingsData> getRiderStandingsList(JSONArray standings) {
		List<RiderStandingsData> result = new ArrayList<>();

		for (int i = 0; i<standings.length(); i++){
			JSONObject rider = standings.getJSONObject(i);

			String name = rider.getJSONObject("driver").getString("name");
			int position = rider.getInt("position");
			double points = rider.getDouble("totalPoints");

			result.add(new RiderStandingsData(name, position, points));
		}
		return result;
	}

	private JSONArray getJsonArrayStandings(Category category, int year) {
		JSONArray result = new JSONArray();

		try {
			String url;
			if (category == Category.MotoGP)
				url = URL_JSON_SEASONS + year + "-" + category.toString().toLowerCase() + "/standings/drivers";
			else
				url = URL_JSON_SEASONS + category.toString().toLowerCase() + "-" + year + "/standings/drivers";

			String refer = URL + "series/" + category.toString().toLowerCase();
			result = (new JSONObject(JsonReader.readJsonFromUrl(url, refer, URL))).getJSONArray("standings");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Returns the URL of the requested grandprix
	 * @param category
	 * @param year
	 * @return String with the URL
	 */
	private String getCompleteURL(Category category, int year) throws IOException {
		String result = URL;
		String grandprix = "";

		String urlRequest;
		if (category == Category.MotoGP)
			urlRequest = URL_JSON_SEASONS + year + "-" + category.toString().toLowerCase() + "/races";
		else
			 urlRequest = URL_JSON_SEASONS + category.toString().toLowerCase() + "-" + year + "/races";

		// Requests the JSONArray containing the races' details
		String referer = URL + "series/" + category.toString().toLowerCase() + "/season/" + year;
		String origin = URL;
		JSONArray races = new JSONArray(JsonReader.readJsonFromUrl(urlRequest, referer, origin));


		if (raceCode != null) {
			int flag = 0;
			for (int i = 0; i<races.length(); i++) {
				JSONObject event = races.getJSONObject(i).getJSONObject("event");
				if (event.getString("code").equals(raceCode.toString())) {
					grandprix = event.getString("uuid");
					break;
				}
				// The Motorsport Stats' website uses the same race code for two different races in the 2002 and 2003 seasons
				else if (event.getString("code").equals("JPN") && raceCode.equals(RaceCode.PAC) && (year == 2003 || year == 2002)) {
					if (flag == 0) {
						flag++;
						continue;
					} else {
						grandprix = event.getString("uuid");
						break;
					}
				}
			}
		} else if (raceNumber > 0) {
			for (int i = 0; i<races.length(); i++) {
				JSONObject event = races.getJSONObject(i).getJSONObject("event");
				if (raceNumber == i + 1) {
					System.out.println("Selected raceCode: " + event.getString("code"));
					grandprix = event.getString("uuid");
					break;
				}
			}
		}

		result += "results/" + grandprix + "/classification/";
		System.out.println(result);
		return result;
	}

	/**
	 * Converts a JSONArray into a List of RiderOnlineData with necessary information
	 * @param classification
	 * @return List of RiderOnlineData
	 */
	private List<RiderOnlineData> getRiderList(JSONArray classification){
		List<RiderOnlineData> result = new ArrayList<>();

		for (int i = 0; i<classification.length(); i++){
			JSONObject rider = classification.getJSONObject(i);

			int position = rider.getInt("finishPosition"); // 0 if not classified
			String name = rider.getJSONArray("drivers").getJSONObject(0).getString("name");
			int time = rider.getInt("time"); // 0 if not available
			int laps = rider.getInt("laps"); // 0 if not available
			String team = rider.getJSONObject("team").getString("name");

			String nationality;
			try{
				nationality = rider.getJSONObject("nationality").getString("name");
			}catch (Exception e){
				nationality = "Not Available";
			}

			int number; // In case the number is not available, this value will be -1
			try{
				number = rider.getInt("carNumber");
			} catch (Exception e){
				//e.printStackTrace();
				number = -1;
			}

			result.add(new RiderOnlineData(number, name, nationality, team, position, time, laps));
		}
		return result;
	}

	/**
	 * Reads the requested classification from the MotorsportStats website
	 * @param category
	 * @param year
	 * @param session
	 * @return JSONObject with the classification data
	 */
	private JSONObject getJsonObjectResults(Category category, int year, Session session) {
		JSONObject result = new JSONObject();

		try {
			String baseURL = getCompleteURL(category, year);
			Element mainResult = null;

			String sessionName = "";
			switch (session){
				case FP1: sessionName = "1st Practice"; break;
				case FP2: sessionName = "2nd Practice"; break;
				case FP3: sessionName = "3rd Practice"; break;
				case FP4: sessionName = "4th Practice"; break;
				case QP1: sessionName = "1st Qualifying"; break;
				case QP2: sessionName = "2nd Qualifying"; break;
				case QP: sessionName = "Qualifying"; break;
				case WARMUP: sessionName = "Warm Up"; break;
				case GRID: sessionName = "Grid"; break;
				case RACE: sessionName = "Race";
			}

			// Gets the button of the selected session based on its text
			int found = 0;
			for (Element temp : Jsoup.connect(baseURL).get().getElementsByClass("_1CDKX").get(1).children()) {
				if (temp.text().equals(sessionName)) {
					mainResult = temp;
					found++;
					break;
				}
			}

			if (found == 0){
				System.out.println("\nThe requested session or data (" + sessionName + ") is not available for the " + year + " season...");
			}

			// Gets the urlCode to request the JSONObject from the website
			String[] temp = mainResult.attr("href").split("/");

			String urlCode = temp[temp.length - 1];
			System.out.println(urlCode);
			String url = URL_JSON_SESSIONS + urlCode + "/classification";
			result = new JSONObject(JsonReader.readJsonFromUrl(url, baseURL, URL_JSON_SESSIONS));

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}

