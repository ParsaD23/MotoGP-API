package com.github.parsad23.motogpapi.reader;

import com.github.parsad23.motogpapi.domain.ConstructorStandings;
import com.github.parsad23.motogpapi.domain.RiderSession;
import com.github.parsad23.motogpapi.domain.RiderStandings;
import com.github.parsad23.motogpapi.domain.TeamStandings;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

class ObjectMapper {

    protected static List<ConstructorStandings> toConstructorStandingsList(JsonArray standings) {
        List<ConstructorStandings> result = new ArrayList<>();

        for (int i = 0; i<standings.size(); i++){
            JsonObject rider = standings.get(i).getAsJsonObject();

            List<Double> results = new ArrayList<>();
            for(JsonElement e : rider.getAsJsonArray("eventPoints"))
                try {
                    results.add(e.getAsDouble());
                } catch (Exception exception) {
                    results.add(0.0);
                }

            result.add(new ConstructorStandings(
                    rider.getAsJsonObject("team").get("name").getAsString(),
                    rider.get("position").getAsInt(),
                    rider.get("totalPoints").getAsDouble(),
                    results));
        }
        return result;
    }

    protected static List<TeamStandings> toTeamStandingsList(JsonArray standings) {
        List<TeamStandings> result = new ArrayList<>();

        for (int i = 0; i < standings.size(); i++) {
            JsonObject rider = standings.get(i).getAsJsonObject();

            List<Double> results = new ArrayList<>();
            for (JsonElement e : rider.getAsJsonArray("eventPoints"))
                try {
                    results.add(e.getAsDouble());
                } catch (Exception exception) {
                    results.add(0.0);
                }

            result.add(new TeamStandings(
                    rider.getAsJsonObject("team").get("name").getAsString(),
                    rider.get("position").getAsInt(),
                    rider.get("totalPoints").getAsDouble(),
                    results));
        }
        return result;
    }

    protected static List<RiderStandings> toRiderStandingsList(JsonArray standings) {
        List<RiderStandings> result = new ArrayList<>();

        for (int i = 0; i<standings.size(); i++){
            JsonObject rider = standings.get(i).getAsJsonObject();

            List<Double> results = new ArrayList<>();
            for(JsonElement e : rider.getAsJsonArray("eventPoints"))
                try {
                    results.add(e.getAsDouble());
                } catch (Exception exception) {
                    results.add(0.0);
                }

            result.add(new RiderStandings(rider.getAsJsonObject("driver").get("name").getAsString(),
                    rider.get("position").getAsInt(),
                    rider.get("totalPoints").getAsDouble(),
                    results));
        }
        return result;
    }

    protected static List<RiderSession> toRiderSessionList(JsonArray classification){
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
