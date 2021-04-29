package com.github.parsad23.motogpapi.reader;

import com.github.parsad23.motogpapi.domain.*;
import com.github.parsad23.motogpapi.exceptions.DataNotAvailableException;
import com.google.gson.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ChampionshipStandingsReader {
    URLGenerator urlGen;

    protected ChampionshipStandingsReader () {
        urlGen = new URLGenerator();
    }

    protected List<TeamStandings> getTeamsStandings(Category category, int year) throws DataNotAvailableException {
        String url = urlGen.getTeamsChampionshipURL(category, year);

        // Requests the JSONArray from the website
        JsonArray jsonArray = getJSONArrayStandings(category, year, url);

        //System.out.println("\nConverting JSONArray to TeamStandings list...");
        //List<TeamStandings> standings = JSONArrayToTeamStandingsList(jsonArray);
        //System.out.println("Result: " + standings);

        return JSONArrayToTeamStandingsList(jsonArray);
    }

    protected List<ConstructorStandings> getConstructorsStandings(Category category, int year) throws DataNotAvailableException {
        String url = urlGen.getConstructorsChampionshipURL(category, year);

        // Requests the JSONArray from the website
        JsonArray jsonArray = getJSONArrayStandings(category, year, url);

        //System.out.println("\nConverting JSONArray to ConstructorStandings list...");
        //List<ConstructorStandings> standings = JSONArrayToConstructorStandingsList(jsonArray);
        //System.out.println("Result: " + standings);

        return JSONArrayToConstructorStandingsList(jsonArray);
    }

    protected List<RiderStandings> getRidersStandings(Category category, int year) throws DataNotAvailableException {
        String url = urlGen.getRidersChampionshipURL(category, year);

        // Requests the JSONArray from the website
        JsonArray jsonArray = getJSONArrayStandings(category, year, url);

        //System.out.println("\nConverting JSONArray to RiderStandings list...");
        //List<RiderStandings> standings = JSONArrayToRiderStandingsList(jsonArray);
        //System.out.println("Result: " + standings);

        return JSONArrayToRiderStandingsList(jsonArray);
    }

    protected List<TeamStandings> getTeamsStandings(Category category, int year, int raceNumber, String raceCode) throws DataNotAvailableException {
        String url = urlGen.getTeamsChampionshipURL(category, year, raceNumber, raceCode);

        // Requests the JSONArray from the website
        JsonArray jsonArray = getJSONArrayStandings(category, year, url);

        //System.out.println("\nConverting JSONArray to TeamStandings list...");
        //List<TeamStandings> standings = JSONArrayToTeamStandingsList(jsonArray);
        //System.out.println("Result: " + standings);

        return JSONArrayToTeamStandingsList(jsonArray);
    }

    protected List<ConstructorStandings> getConstructorsStandings(Category category, int year, int raceNumber, String raceCode) throws DataNotAvailableException {
        String url = urlGen.getConstructorsChampionshipURL(category, year, raceNumber, raceCode);

        // Requests the JSONArray from the website
        JsonArray jsonArray = getJSONArrayStandings(category, year, url);

        //System.out.println("\nConverting JSONArray to ConstructorStandings list...");
        //List<ConstructorStandings> standings = JSONArrayToConstructorStandingsList(jsonArray);
        //System.out.println("Result: " + standings);

        return JSONArrayToConstructorStandingsList(jsonArray);
    }

    protected List<RiderStandings> getRidersStandings(Category category, int year, int raceNumber, String raceCode) throws DataNotAvailableException {
        String url = urlGen.getRidersChampionshipURL(category, year, raceNumber, raceCode);

        // Requests the JSONArray from the website
        JsonArray jsonArray = getJSONArrayStandings(category, year, url);

        //System.out.println("\nConverting JSONArray to RiderStandings list...");
        //List<RiderStandings> standings = JSONArrayToRiderStandingsList(jsonArray);
        //System.out.println("Result: " + standings);

        return JSONArrayToRiderStandingsList(jsonArray);
    }

    private List<ConstructorStandings> JSONArrayToConstructorStandingsList(JsonArray standings) {
        List<ConstructorStandings> result = new ArrayList<>();

        for (int i = 0; i<standings.size(); i++){
            JsonObject rider = standings.get(i).getAsJsonObject();

            String name = rider.getAsJsonObject("team").get("name").getAsString();
            int position = rider.get("position").getAsInt();
            double points = rider.get("totalPoints").getAsDouble();
            List<Double> results = new ArrayList<>();
            for(JsonElement e : rider.getAsJsonArray("eventPoints"))
                try {
                    results.add(e.getAsDouble());
                } catch (Exception exception) {
                    results.add(0.0);
                }

            result.add(new ConstructorStandings(name, position, points, results));
        }
        return result;
    }

    private List<TeamStandings> JSONArrayToTeamStandingsList(JsonArray standings) {
        List<TeamStandings> result = new ArrayList<>();

        for (int i = 0; i<standings.size(); i++){
            JsonObject rider = standings.get(i).getAsJsonObject();

            String name = rider.getAsJsonObject("team").get("name").getAsString();
            int position = rider.get("position").getAsInt();
            double points = rider.get("totalPoints").getAsDouble();
            List<Double> results = new ArrayList<>();
            for(JsonElement e : rider.getAsJsonArray("eventPoints"))
                try {
                    results.add(e.getAsDouble());
                } catch (Exception exception) {
                    results.add(0.0);
                }
            result.add(new TeamStandings(name, position, points, results));
        }
        return result;
    }

    private List<RiderStandings> JSONArrayToRiderStandingsList(JsonArray standings) {
        List<RiderStandings> result = new ArrayList<>();

        for (int i = 0; i<standings.size(); i++){
            JsonObject rider = standings.get(i).getAsJsonObject();

            String name = rider.getAsJsonObject("driver").get("name").getAsString();
            int position = rider.get("position").getAsInt();
            double points = rider.get("totalPoints").getAsDouble();
            List<Double> results = new ArrayList<>();
            for(JsonElement e : rider.getAsJsonArray("eventPoints"))
                try {
                    results.add(e.getAsDouble());
                } catch (Exception exception) {
                    results.add(0.0);
                }

            result.add(new RiderStandings(name, position, points, results));
        }
        return result;
    }

    private JsonArray getJSONArrayStandings(Category category, int year, String url) throws DataNotAvailableException {
        JsonArray result;

        try {
            //System.out.print("\nRequesting JSONArray with the standings...");
            String refer = urlGen.getBase_url() + "series/" + category.toString().toLowerCase();
            result = (JsonParser.parseString(JsonReader.readJsonFromUrl(url, refer, urlGen.getBase_url())).getAsJsonObject()).getAsJsonArray("standings");

        } catch (IOException e) {
            throw new DataNotAvailableException("The requested standings do not exist or are not available");
        }

        return result;
    }
}
