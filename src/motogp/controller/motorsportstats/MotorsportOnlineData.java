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

	/**
	 * If the results are not available or the selected session does not exist, getResults() will return an empty list.
	 * @param category
	 * @param year
	 * @param code
	 * @param session
	 * @return
	 */
	@Override
	public List<RiderOnlineData> getResults(Category category, int year, RaceCode code, Session session) {
		List<RiderOnlineData> result = new ArrayList<>();

		try {
			// Requests the JSON object from the website
			JSONObject jsonObject = getJsonObject(category, year, code, session);

			// Converts the JSON object into a list of RiderOnlineData
			JSONArray gridJSON = jsonObject.getJSONArray("details");
			result = getRiderList(gridJSON);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			return result;
		}
	}

	/**
	 * Returns the URL of the requested grandprix
	 * @param category
	 * @param year
	 * @param code
	 * @return String with the URL
	 */
	private String getCompleteURL(Category category, int year, RaceCode code) throws IOException {
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

		// Searches the URL code of the selected race
		int flag = 0;
		for (int i = 0; i<races.length(); i++){
			JSONObject event = races.getJSONObject(i).getJSONObject("event");
			if (event.getString("code").equals(code.toString())){
				grandprix = event.getString("uuid");
				break;
			} else if (event.getString("code").equals("JPN") && code.equals(RaceCode.PAC) && (year == 2003 || year == 2002)) {
				if (flag == 0) {
					flag++;
					continue;
				} else {
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
			String team = rider.getJSONObject("team").getString("name");
			int number; // In case the number is not available, this value will be -1
			try{
				number = rider.getInt("carNumber");
			} catch (Exception e){
				//e.printStackTrace();
				number = -1;
			}

			result.add(new RiderOnlineData(number, name, team, position));
		}
		return result;
	}

	/**
	 * Reads the requested classification from the MotorsportStats website
	 * @param category
	 * @param year
	 * @param code
	 * @param session
	 * @return JSONObject with the classification data
	 */
	private JSONObject getJsonObject(Category category, int year, RaceCode code, Session session) {
		JSONObject result = new JSONObject();

		try {
			String baseURL = getCompleteURL(category, year, code);
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

