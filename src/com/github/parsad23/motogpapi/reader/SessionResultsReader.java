package com.github.parsad23.motogpapi.reader;

import com.github.parsad23.motogpapi.domain.Category;
import com.github.parsad23.motogpapi.domain.RiderSession;
import com.github.parsad23.motogpapi.domain.Session;
import com.github.parsad23.motogpapi.exceptions.DataNotAvailableException;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class SessionResultsReader {

    private String raceCode;
    private int raceNumber;

    protected List<RiderSession> getSessionResults(Category category, int year, String raceCode, int raceNumber,Session session) throws DataNotAvailableException {
        this.raceCode = raceCode;
        this.raceNumber = raceNumber;

        // Requests the JSONObject from the website
        JsonObject jsonObject = getJSONObjectResults(category, year, session);

        //System.out.println("\nConverting JSONObject to RiderSession...");
        JsonArray gridJSON = jsonObject.getAsJsonArray("details");
        //List<RiderSession> result = JSONArrayToRiderSessionList(gridJSON);

        //System.out.println("Result: " + result);

        return JSONArrayToRiderSessionList(gridJSON);
    }


    private JsonObject getJSONObjectResults(Category category, int year, Session session) throws DataNotAvailableException {
        JsonObject result;
        URLGenerator urlGen = new URLGenerator();
        String sessionResultsURL = urlGen.getSessionResultsURL(category, year, session, raceNumber, raceCode);
        try {
            result = (JsonParser.parseString(JsonReader.readJsonFromUrl(sessionResultsURL, urlGen.getBase_url(), urlGen.getUrl_json_sessions()))).getAsJsonObject();
        } catch (IOException e){
            throw new DataNotAvailableException("The requested session or data does not exist or is not available");
        }
        return result;
    }

    private List<RiderSession> JSONArrayToRiderSessionList(JsonArray classification){
        List<RiderSession> result = new ArrayList<>();

        for (int i = 0; i<classification.size(); i++){
            JsonObject rider = classification.get(i).getAsJsonObject();

            int position = rider.get("finishPosition").getAsInt(); // 0 if not classified
            String name = rider.getAsJsonArray("drivers").get(0).getAsJsonObject().get("name").getAsString();
            int time = rider.get("time").getAsInt(); // 0 if not available
            int laps = rider.get("laps").getAsInt(); // 0 if not available
            String team = rider.getAsJsonObject("team").get("name").getAsString();

            String nationality;
            try{
                nationality = rider.getAsJsonObject("nationality").get("name").getAsString();
            }catch (Exception e){
                nationality = "Not Available";
            }

            int number; // In case the number is not available, this value will be -1
            try{
                number = rider.get("carNumber").getAsInt();
            } catch (Exception e){
                number = -1;
            }

            result.add(new RiderSession(number, name, nationality, team, position, time, laps));
        }
        return result;
    }

}
