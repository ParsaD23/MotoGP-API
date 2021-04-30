package com.github.parsad23.motogpapi.reader;

import com.github.parsad23.motogpapi.domain.*;
import com.github.parsad23.motogpapi.exceptions.DataNotAvailableException;
import com.google.gson.*;

import java.io.IOException;
import java.util.List;

class ChampionshipStandingsReader {

    protected static List<TeamStandings> getTeamsStandings(Category category, int year) throws DataNotAvailableException {
        String url = URLGenerator.getTeamsChampionshipURL(category, year);
        JsonArray jsonArray = getJSONArrayStandings(category, url); // Requesting data from website
        return ObjectMapper.toTeamStandingsList(jsonArray);
    }

    protected static List<TeamStandings> getTeamsStandings(Category category, int year, int raceNumber, String raceCode) throws DataNotAvailableException {
        String url = URLGenerator.getTeamsChampionshipURL(category, year, raceNumber, raceCode);
        JsonArray jsonArray = getJSONArrayStandings(category, url); // Requesting data from website
        return ObjectMapper.toTeamStandingsList(jsonArray);
    }

    protected static List<ConstructorStandings> getConstructorsStandings(Category category, int year) throws DataNotAvailableException {
        String url = URLGenerator.getConstructorsChampionshipURL(category, year);
        JsonArray jsonArray = getJSONArrayStandings(category, url); // Requesting data from website
        return ObjectMapper.toConstructorStandingsList(jsonArray);
    }

    protected static List<ConstructorStandings> getConstructorsStandings(Category category, int year, int raceNumber, String raceCode) throws DataNotAvailableException {
        String url = URLGenerator.getConstructorsChampionshipURL(category, year, raceNumber, raceCode);
        JsonArray jsonArray = getJSONArrayStandings(category, url); // Requesting data from website
        return ObjectMapper.toConstructorStandingsList(jsonArray);
    }
    protected static List<RiderStandings> getRidersStandings(Category category, int year) throws DataNotAvailableException {
        String url = URLGenerator.getRidersChampionshipURL(category, year);
        JsonArray jsonArray = getJSONArrayStandings(category, url); // Requesting data from website
        return ObjectMapper.toRiderStandingsList(jsonArray);
    }

    protected static List<RiderStandings> getRidersStandings(Category category, int year, int raceNumber, String raceCode) throws DataNotAvailableException {
        String url = URLGenerator.getRidersChampionshipURL(category, year, raceNumber, raceCode);
        JsonArray jsonArray = getJSONArrayStandings(category, url); // Requesting data from website
        return ObjectMapper.toRiderStandingsList(jsonArray);
    }

    private static JsonArray getJSONArrayStandings(Category category, String url) throws DataNotAvailableException {
        JsonArray result;

        try {
            String refer = URLGenerator.base_url + "series/" + category.toString().toLowerCase();
            result = (JsonParser.parseString(JsonReader.readJsonFromUrl(url, refer, URLGenerator.base_url)).getAsJsonObject()).getAsJsonArray("standings");

        } catch (IOException e) {
            throw new DataNotAvailableException("The requested standings do not exist or are not available");
        }

        return result;
    }
}
