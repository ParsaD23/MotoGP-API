package com.github.parsad23.motogpapi.reader;

import com.github.parsad23.motogpapi.domain.Category;
import com.github.parsad23.motogpapi.domain.Session;
import com.github.parsad23.motogpapi.exceptions.DataNotAvailableException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

class URLGenerator {

    private final String url_json_seasons = "https://mssproxy.motorsportstats.com/web/3.0.0/seasons/";
    private final String url_json_sessions = "https://mssproxy.motorsportstats.com/web/3.0.0/sessions/";
    private final String base_url = "https://results.motorsportstats.com/";

    protected String getUrl_json_seasons() {
        return url_json_seasons;
    }

    protected String getUrl_json_sessions() {
        return url_json_sessions;
    }

    protected String getBase_url() {
        return base_url;
    }

    protected String getRidersChampionshipURL(Category category, int year){
        if (category == Category.MotoGP)
            return url_json_seasons + year + "-" + category.toString().toLowerCase() + "/standings/drivers/";
        return url_json_seasons + category.toString().toLowerCase() + "-" + year + "/standings/drivers/";
    }

    protected String getConstructorsChampionshipURL(Category category, int year){
        if (category == Category.MotoGP)
            return url_json_seasons + year + "-" + category.toString().toLowerCase() + "/standings/constructors/";
        return url_json_seasons + category.toString().toLowerCase() + "-" + year + "/standings/constructors/";
    }

    protected String getTeamsChampionshipURL(Category category, int year){
        if (category == Category.MotoGP)
            return url_json_seasons + year + "-" + category.toString().toLowerCase() + "/standings/teams/";
        return url_json_seasons + category.toString().toLowerCase() + "-" + year + "/standings/teams/";
    }

    protected String getRidersChampionshipURL(Category category, int year, int raceNumber, String raceCode) throws DataNotAvailableException {
        String eventCode = getEventCode(category, year, raceNumber, raceCode);
        if (category == Category.MotoGP)
            return url_json_seasons + year + "-" + category.toString().toLowerCase() + "/standings/drivers/?eventSlug="+eventCode;
        return url_json_seasons + category.toString().toLowerCase() + "-" + year + "/standings/drivers/?eventSlug="+eventCode;
    }

    protected String getConstructorsChampionshipURL(Category category, int year, int raceNumber, String raceCode) throws DataNotAvailableException{
        String eventCode = getEventCode(category, year, raceNumber, raceCode);
        if (category == Category.MotoGP)
            return url_json_seasons + year + "-" + category.toString().toLowerCase() + "/standings/constructors/?eventSlug="+eventCode;
        return url_json_seasons + category.toString().toLowerCase() + "-" + year + "/standings/constructors/?eventSlug="+eventCode;
    }

    protected String getTeamsChampionshipURL(Category category, int year, int raceNumber, String raceCode) throws DataNotAvailableException {
        String eventCode = getEventCode(category, year, raceNumber, raceCode);
        if (category == Category.MotoGP)
            return url_json_seasons + year + "-" + category.toString().toLowerCase() + "/standings/teams/?eventSlug="+eventCode;
        return url_json_seasons + category.toString().toLowerCase() + "-" + year + "/standings/teams/?eventSlug="+eventCode;
    }

    protected String getSessionResultsPageURL(Category category, int year, int raceNumber, String raceCode) throws DataNotAvailableException {
        String grandprix = getEventCode(category, year, raceNumber, raceCode);

        return base_url + "results/" + grandprix + "/";
    }

    protected String getSessionResultsURL(Category category, int year, Session session, int raceNumber, String raceCode) throws DataNotAvailableException {
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

    protected String getEventCode(Category category, int year, int raceNumber, String raceCode) throws DataNotAvailableException {
        String url;
        String grandprix = "";

        if (category == Category.MotoGP)
            url = url_json_seasons + year + "-" + category.toString().toLowerCase() + "/races/";
        else
            url = url_json_seasons + category.toString().toLowerCase() + "-" + year + "/races/";
        String referer = base_url + "series/" + category.toString().toLowerCase() + "/season/" + year + "/";

        JSONArray races;
        try{
            races = new JSONArray(JsonReader.readJsonFromUrl(url, referer, base_url));
        } catch (IOException e){
            throw new DataNotAvailableException("Unable to read the JSONArray containing the races: empty String returned...");
        }

        if (raceCode != null) {
            for (int i = 0; i<races.length(); i++) {
                JSONObject event = races.getJSONObject(i).getJSONObject("event");
                if (event.getString("code").equalsIgnoreCase(raceCode)) {
                    //System.out.println("Selected raceCode: " + event.getString("code"));
                    grandprix = event.getString("uuid");
                    break;
                }
            }
        } else if (raceNumber > 0) {
            for (int i = 0; i < races.length(); i++) {
                JSONObject event = races.getJSONObject(i).getJSONObject("event");
                if (raceNumber == i + 1) {
                    //System.out.println("Selected raceCode: " + event.getString("code"));
                    grandprix = event.getString("uuid");
                    break;
                }
            }
        }
        return grandprix;
    }
}
