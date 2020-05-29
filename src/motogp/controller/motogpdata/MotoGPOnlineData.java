package motogp.controller.motogpdata;

import motogp.model.*;

import java.util.List;

public interface MotoGPOnlineData {

	List<RiderOnlineData> getResults(Category category, int year, RaceCode code, Session session);
	List<RiderStandingsData> getChampionshipStandings(Category category, int year);

}
