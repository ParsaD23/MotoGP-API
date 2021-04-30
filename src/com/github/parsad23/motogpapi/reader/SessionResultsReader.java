package com.github.parsad23.motogpapi.reader;

import com.github.parsad23.motogpapi.domain.Category;
import com.github.parsad23.motogpapi.domain.RiderSession;
import com.github.parsad23.motogpapi.domain.Session;
import com.github.parsad23.motogpapi.exceptions.DataNotAvailableException;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.List;

class SessionResultsReader {

    protected static List<RiderSession> getSessionResults(Category category, int year, String raceCode, int raceNumber,Session session) throws DataNotAvailableException {
        JsonObject jsonObject = getJSONObjectResults(category, year, raceCode, raceNumber, session); // Requesting data from website
        JsonArray gridJSON = jsonObject.getAsJsonArray("details");
        return ObjectMapper.toRiderSessionList(gridJSON);
    }


    private static JsonObject getJSONObjectResults(Category category, int year, String raceCode, int raceNumber, Session session) throws DataNotAvailableException {
        JsonObject result;
        String sessionResultsURL = URLGenerator.getSessionResultsURL(category, year, session, raceNumber, raceCode);
        try {
            result = (JsonParser.parseString(JsonReader.readJsonFromUrl(sessionResultsURL, URLGenerator.base_url, URLGenerator.url_json_sessions))).getAsJsonObject();
        } catch (IOException e){
            throw new DataNotAvailableException("The requested session or data does not exist or is not available");
        }
        return result;
    }

}
