package com.github.parsad23.motogpapi.reader;

import com.github.parsad23.motogpapi.domain.Category;
import com.github.parsad23.motogpapi.domain.ConstructorStandings;
import com.github.parsad23.motogpapi.domain.RiderStandings;
import com.github.parsad23.motogpapi.domain.TeamStandings;
import com.github.parsad23.motogpapi.exceptions.DataNotAvailableException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class ChampionshipStandingsReader {
    URLGenerator urlGen;

    protected ChampionshipStandingsReader () throws IOException {
        urlGen = new URLGenerator();
    }

    protected List<TeamStandings> getTeamsStandings(Category category, int year) throws DataNotAvailableException {
        String url = urlGen.getTeamsChampionshipURL(category, year);

        // Requests the JSONArray from the website
        JSONArray jsonArray = getJSONArrayStandings(category, year, url);

        //System.out.println("\nConverting JSONArray to TeamStandings list...");
        List<TeamStandings> standings = JSONArrayToTeamStandingsList(jsonArray);
        //System.out.println("Result: " + standings);

        return standings;
    }

    protected List<ConstructorStandings> getConstructorsStandings(Category category, int year) throws DataNotAvailableException {
        String url = urlGen.getConstructorsChampionshipURL(category, year);

        // Requests the JSONArray from the website
        JSONArray jsonArray = getJSONArrayStandings(category, year, url);

        //System.out.println("\nConverting JSONArray to ConstructorStandings list...");
        List<ConstructorStandings> standings = JSONArrayToConstructorStandingsList(jsonArray);
        //System.out.println("Result: " + standings);

        return standings;
    }

    protected List<RiderStandings> getRidersStandings(Category category, int year) throws DataNotAvailableException {
        String url = urlGen.getRidersChampionshipURL(category, year);

        // Requests the JSONArray from the website
        JSONArray jsonArray = getJSONArrayStandings(category, year, url);

        //System.out.println("\nConverting JSONArray to RiderStandings list...");
        List<RiderStandings> standings = JSONArrayToRiderStandingsList(jsonArray);
        //System.out.println("Result: " + standings);

        return standings;
    }

    protected List<TeamStandings> getTeamsStandings(Category category, int year, int raceNumber, String raceCode) throws DataNotAvailableException {
        String url = urlGen.getTeamsChampionshipURL(category, year, raceNumber, raceCode);

        // Requests the JSONArray from the website
        JSONArray jsonArray = getJSONArrayStandings(category, year, url);

        //System.out.println("\nConverting JSONArray to TeamStandings list...");
        List<TeamStandings> standings = JSONArrayToTeamStandingsList(jsonArray);
        //System.out.println("Result: " + standings);

        return standings;
    }

    protected List<ConstructorStandings> getConstructorsStandings(Category category, int year, int raceNumber, String raceCode) throws DataNotAvailableException {
        String url = urlGen.getConstructorsChampionshipURL(category, year, raceNumber, raceCode);

        // Requests the JSONArray from the website
        JSONArray jsonArray = getJSONArrayStandings(category, year, url);

        //System.out.println("\nConverting JSONArray to ConstructorStandings list...");
        List<ConstructorStandings> standings = JSONArrayToConstructorStandingsList(jsonArray);
        //System.out.println("Result: " + standings);

        return standings;
    }

    protected List<RiderStandings> getRidersStandings(Category category, int year, int raceNumber, String raceCode) throws DataNotAvailableException {
        String url = urlGen.getRidersChampionshipURL(category, year, raceNumber, raceCode);

        // Requests the JSONArray from the website
        JSONArray jsonArray = getJSONArrayStandings(category, year, url);

        //System.out.println("\nConverting JSONArray to RiderStandings list...");
        List<RiderStandings> standings = JSONArrayToRiderStandingsList(jsonArray);
        //System.out.println("Result: " + standings);

        return standings;
    }

    private List<ConstructorStandings> JSONArrayToConstructorStandingsList(JSONArray standings) {
        List<ConstructorStandings> result = new ArrayList<>();

        for (int i = 0; i<standings.length(); i++){
            JSONObject constructor = standings.getJSONObject(i);

            String name = constructor.getJSONObject("team").getString("name");
            int position = constructor.getInt("position");
            double points = constructor.getDouble("totalPoints");
            List<Double> results = new ArrayList<>();
            for(Object e : constructor.getJSONArray("eventPoints").toList())
                try {
                    results.add(((BigDecimal) e).doubleValue());
                } catch (Exception exception) {
                    results.add(0.0);
                }

            result.add(new ConstructorStandings(name, position, points, results));
        }
        return result;
    }

    private List<TeamStandings> JSONArrayToTeamStandingsList(JSONArray standings) {
        List<TeamStandings> result = new ArrayList<>();

        for (int i = 0; i<standings.length(); i++){
            JSONObject team = standings.getJSONObject(i);

            String name = team.getJSONObject("team").getString("name");
            int position = team.getInt("position");
            double points = team.getDouble("totalPoints");
            List<Double> results = new ArrayList<>();
            for(Object e : team.getJSONArray("eventPoints").toList())
                try {
                    results.add(((BigDecimal) e).doubleValue());
                } catch (Exception exception) {
                    results.add(0.0);
                }
            result.add(new TeamStandings(name, position, points, results));
        }
        return result;
    }

    private List<RiderStandings> JSONArrayToRiderStandingsList(JSONArray standings) {
        List<RiderStandings> result = new ArrayList<>();

        for (int i = 0; i<standings.length(); i++){
            JSONObject rider = standings.getJSONObject(i);

            String name = rider.getJSONObject("driver").getString("name");
            int position = rider.getInt("position");
            double points = rider.getDouble("totalPoints");
            List<Double> results = new ArrayList<>();
            for(Object e : rider.getJSONArray("eventPoints").toList())
                try {
                    results.add(((BigDecimal) e).doubleValue());
                } catch (Exception exception) {
                    results.add(0.0);
                }

            result.add(new RiderStandings(name, position, points, results));
        }
        return result;
    }

    private JSONArray getJSONArrayStandings(Category category, int year, String url) throws DataNotAvailableException {
        JSONArray result = new JSONArray();

        try {
            //System.out.print("\nRequesting JSONArray with the standings...");
            String refer = urlGen.getBase_url() + "series/" + category.toString().toLowerCase();
            result = (new JSONObject(JsonReader.readJsonFromUrl(url, refer, urlGen.getBase_url()))).getJSONArray("standings");

        } catch (IOException e) {
            throw new DataNotAvailableException("The requested standings do not exist or are not available");
        }

        return result;
    }
}
