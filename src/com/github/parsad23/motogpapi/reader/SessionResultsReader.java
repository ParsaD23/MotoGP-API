package com.github.parsad23.motogpapi.reader;

import com.github.parsad23.motogpapi.domain.Category;
import com.github.parsad23.motogpapi.domain.RiderSession;
import com.github.parsad23.motogpapi.domain.Session;
import com.github.parsad23.motogpapi.exceptions.DataNotAvailableException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class SessionResultsReader {

    private String raceCode;
    private int raceNumber;

    protected List<RiderSession> getSessionResults(Category category, int year, String raceCode, int raceNumber,Session session) throws IOException, DataNotAvailableException {
        this.raceCode = raceCode;
        this.raceNumber = raceNumber;

        // Requests the JSONObject from the website
        JSONObject jsonObject = getJSONObjectResults(category, year, session);

        //System.out.println("\nConverting JSONObject to RiderSession...");
        JSONArray gridJSON = jsonObject.getJSONArray("details");
        List<RiderSession> result = JSONArrayToRiderSessionList(gridJSON);

        //System.out.println("Result: " + result);

        return result;
    }


    private JSONObject getJSONObjectResults(Category category, int year, Session session) throws DataNotAvailableException, IOException {
        JSONObject result = new JSONObject();
        URLGenerator urlGen = new URLGenerator();
        String sessionResultsURL = urlGen.getSessionResultsURL(category, year, session, raceNumber, raceCode);
        try {
            result = new JSONObject(JsonReader.readJsonFromUrl(sessionResultsURL, urlGen.getBase_url(), urlGen.getUrl_json_sessions()));
        } catch (IOException e){
            throw new DataNotAvailableException("The requested session or data does not exist or is not available");
        }
        return result;
    }

    private List<RiderSession> JSONArrayToRiderSessionList(JSONArray classification){
        List<RiderSession> result = new ArrayList<>();

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
                number = -1;
            }

            result.add(new RiderSession(number, name, nationality, team, position, time, laps));
        }
        return result;
    }

}
