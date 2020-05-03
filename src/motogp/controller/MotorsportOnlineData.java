package motogp.controller;

import motogp.model.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MotorsportOnlineData implements MotoGPOnlineData{

	private static final String URL = "https://results.motorsportstats.com/";
	private static final String URL_JSON = "https://mssproxy.motorsportstats.com/web/3.0.0/sessions/";
	private static final String YEAR = "2019";

	@Override
	public List<RiderOnlineData> getGrid(int year, RaceCode code) {
		JSONObject jsonObject = getJsonObject(year, code, false);

		JSONArray gridJSON = jsonObject.getJSONArray("details");
		List<RiderOnlineData> result = getRiderList(gridJSON);

		return result;

	}

	@Override
	public List<RiderOnlineData> getRaceResults(int year, RaceCode code) {
		JSONObject jsonObject = getJsonObject(year, code, true);

		JSONArray raceJSON = jsonObject.getJSONArray("details");
		List<RiderOnlineData> result = getRiderList(raceJSON);

		return result;
	}

	private String getCompleteURL(int year, RaceCode code){
		String result = URL;
		String race = "";

		switch (code) {
			case QAT:
				race = "qatar-grand-prix";
				break;
			case ARG:
				race = "argentine-grand-prix";
				break;
			case AME:
				race = "grand-prix-of-the-americas";
				break;
			case SPA:
				race = "spanish-grand-prix";
				break;
			case FRA:
				race = "french-grand-prix-2";
				break;
			case ITA:
				race = "italian-grand-prix-2";
				break;
			case CAT:
				race = "catalan-grand-prix";
				break;
			case NED:
				race = "dutch-grand-prix";
				break;
			case GER:
				race = "german-grand-prix";
				break;
			case CZE:
				race = "czech-grand-prix";
				break;
			case AUT:
				race = "austrian-grand-prix";
				break;
			case GBR:
				race = "british-grand-prix";
				break;
			case RSM:
				race = "san-marino-grand-prix";
				break;
			case ARA:
				race = "aragon-grand-prix";
				break;
			case THA:
				race = "thai-grand-prix";
				break;
			case JPN:
				race = "japanese-grand-prix";
				break;
			case AUS:
				race = "australian-grand-prix-2";
				break;
			case MAL:
				race = "malaysian-grand-prix";
				break;
			case VAL:
				race = "community-valencia-grand-prix";
				break;
		}

		result += "results/" + year + "-" + race + "/classification/";

		return result;
	}

	private List<RiderOnlineData> getRiderList(JSONArray gridJSON){
		List<RiderOnlineData> result = new ArrayList<>();

		for (int i = 0; i<gridJSON.length(); i++){
			JSONObject rider = gridJSON.getJSONObject(i);

			int position = rider.getInt("finishPosition"); // 0 if not classified
			int number = rider.getInt("carNumber");

			String teamCode = rider.getJSONObject("team").getString("code");
			Constructor constructor;

			if (teamCode.equals("SIC") || teamCode.equals("YMR"))
				constructor = Constructor.Yamaha;
			else if (teamCode.equals("DUC") || teamCode.equals("AVI") || teamCode.equals("PRA"))
				constructor = Constructor.Ducati;
			else if (teamCode.equals("HOND") || teamCode.equals("LCR"))
				constructor = Constructor.Honda;
			else if (teamCode.equals("GRE"))
				constructor = Constructor.Aprilia;
			else if (teamCode.equals("TEC") || teamCode.equals("KTM"))
				constructor = Constructor.KTM;
			else if (teamCode.equals("SUZ"))
				constructor = Constructor.Suzuki;
			else
				constructor = Constructor.NotDefined;

			result.add(new RiderOnlineData(number, position, constructor));
		}

		return result;

	}

	private JSONObject getJsonObject(int year, RaceCode code, boolean race) {
		JSONObject result = new JSONObject();

		try {
			String baseURL = getCompleteURL(year, code);
			Element mainResult;
			if (!race)
				mainResult = Jsoup.connect(baseURL).get().getElementsByClass("uaJW4 _2j84j").get(7);
			else
				mainResult = Jsoup.connect(baseURL).get().getElementsByClass("uaJW4 _2j84j _2-xjJ").first();
			String[] temp = mainResult.attr("href").split("/");
			String gridUrlCode = temp[temp.length - 1];
			String gridURL = URL_JSON + gridUrlCode + "/classification";
			//System.out.println("JSONObject :\n"+gridURL);
			result = JsonReader.readJsonFromUrl(baseURL, gridURL, URL_JSON);
			//System.out.println(result.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}

