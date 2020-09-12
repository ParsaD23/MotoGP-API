package motogp.controller.motogpdata;

import motogp.model.*;

import java.util.List;

public interface MotoGPOnlineData {

	List<RiderOnlineData> getResultsByRaceCode(Category category, int year, RaceCode code, Session session);
	List<RiderOnlineData> getResultsByRaceNumber(Category category, int year, int raceNumber, Session session);
	List<RiderStandingsData> getChampionshipStandings(Category category, int year);

}
