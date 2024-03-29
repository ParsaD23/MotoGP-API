package com.github.parsad23.motogpapi.reader;

import com.github.parsad23.motogpapi.domain.Category;
import com.github.parsad23.motogpapi.domain.Session;
import com.github.parsad23.motogpapi.exceptions.DataNotAvailableException;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;

class URLGenerator {

    protected static final String url_json_seasons = "https://mssproxy.motorsportstats.com/web/3.0.0/seasons/";
    protected static final String url_json_sessions = "https://mssproxy.motorsportstats.com/web/3.0.0/sessions/";
    protected static final String base_url = "https://results.motorsportstats.com/";

    protected static String getRidersChampionshipURL(Category category, int year){
        return getStandingsURL(category, year) + "drivers/";
    }

    protected static String getConstructorsChampionshipURL(Category category, int year){
        return getStandingsURL(category, year) + "constructors/";
    }

    protected static String getTeamsChampionshipURL(Category category, int year){
        return getStandingsURL(category, year) + "teams/";
    }

    protected static String getRidersChampionshipURL(Category category, int year, int raceNumber, String raceCode) throws DataNotAvailableException {
        String eventCode = getEventCode(category, year, raceNumber, raceCode);
        return getStandingsURL(category, year) + "drivers/?eventSlug="+eventCode;
    }

    protected static String getConstructorsChampionshipURL(Category category, int year, int raceNumber, String raceCode) throws DataNotAvailableException{
        String eventCode = getEventCode(category, year, raceNumber, raceCode);
        return getStandingsURL(category, year) + "constructors/?eventSlug="+eventCode;
    }

    protected static String getTeamsChampionshipURL(Category category, int year, int raceNumber, String raceCode) throws DataNotAvailableException {
        String eventCode = getEventCode(category, year, raceNumber, raceCode);
        return getStandingsURL(category, year) + "teams/?eventSlug="+eventCode;
    }

    protected static String getSessionResultsPageURL(Category category, int year, int raceNumber, String raceCode) throws DataNotAvailableException {
        String grandprix = getEventCode(category, year, raceNumber, raceCode);

        return base_url + "results/" + grandprix + "/";
    }

    protected static String getSessionResultsURL(Category category, int year, Session session, int raceNumber, String raceCode) throws DataNotAvailableException {
        String sessionResultsPageURL = getSessionResultsPageURL(category, year, raceNumber, raceCode) + "classification/";
        Element mainResult = null;

        // Gets the button of the selected session based on its text
        int found = 0;
        try {
            for (Element temp : Jsoup.connect(sessionResultsPageURL).get().getElementsByClass("_1CDKX").get(1).children()) {
                if (session.getValues().contains(temp.text().toLowerCase())) {
                    mainResult = temp;
                    found++;
                    break;
                }
            }
        } catch (IOException e){
            throw new DataNotAvailableException("The requested session or data does not exist or is not available");
        }

        if (found == 0){
            System.err.println("\nThe requested session or data (" + session.getValues().get(0).toUpperCase() + ") is not available for the " + year + " season...");
        }

        String[] temp;
        try{
            assert mainResult != null;
            temp = mainResult.attr("href").split("/");
        } catch (NullPointerException e){
            throw new DataNotAvailableException("The requested session or data does not exist or is not available");
        }

        String urlCode = temp[temp.length - 1];

        return url_json_sessions + urlCode + "/classification";
    }

    protected static String getEventCode(Category category, int year, int raceNumber, String raceCode) throws DataNotAvailableException {
        String url;
        String grandprix = "";

        if (category == Category.MotoGP)
            url = url_json_seasons + year + "-world-championship-2" + "/races/";
        else
            url = url_json_seasons + category.toString().toLowerCase() + "-" + year + "/races/";
        String referer = base_url + "series/" + category.toString().toLowerCase() + "/season/" + year + "/";

        JsonArray races;
        try{
            races = JsonParser.parseString(JsonReader.readJsonFromUrl(url, referer, base_url)).getAsJsonArray();
        } catch (IOException e){
            throw new DataNotAvailableException("Unable to read the JSONArray containing the races: empty String returned...");
        }

        if (raceCode != null) {
            for (int i = 0; i<races.size(); i++) {
                JsonObject event = races.get(i).getAsJsonObject().get("event").getAsJsonObject();
                if (event.get("code").getAsString().equalsIgnoreCase(raceCode)) {
                    //System.out.println("Selected raceCode: " + event.getString("code"));
                    grandprix = event.get("uuid").getAsString();
                    break;
                }
            }
        } else if (raceNumber > 0) {
            for (int i = 0; i < races.size(); i++) {
                JsonObject event = races.get(i).getAsJsonObject().get("event").getAsJsonObject();
                if (raceNumber == i + 1) {
                    //System.out.println("Selected raceCode: " + event.getString("code"));
                    grandprix = event.get("uuid").getAsString();
                    break;
                }
            }
        }
        return grandprix;
    }

    private static String getStandingsURL(Category category, int year) {
        if (category == Category.MotoGP)
            return url_json_seasons + year + "-" + "world-championship-2" + "/standings/";
        return url_json_seasons + category.toString().toLowerCase() + "-" + year + "/standings/";
    }

}
