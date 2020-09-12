package motogp.controller.motogpdata;

import motogp.controller.motorsportstats.MotorsportOnlineData;
import motogp.model.*;

import java.util.List;


public class MotoGPData {

	private MotoGPOnlineData data;

	public MotoGPData(){
		data = new MotorsportOnlineData();
	}

	/**
	 * If the results are not available or the session does not exist, an empty List will be returned.
	 * @param category
	 * @param year
	 * @param raceCode
	 * @param session
	 * @return
	 */
	public List<RiderOnlineData> getResultsByRaceCode(Category category, int year, RaceCode raceCode, Session session){
		return data.getResultsByRaceCode(category, year, raceCode, session);
	}

	/**
	 * If the results are not available or the session does not exist, an empty List will be returned.
	 * The raceNumber's index start from 1; for example, in order to get the results of the first race set the raceNumber variable to 1.
	 * @param category
	 * @param year
	 * @param raceNumber
	 * @param session
	 * @return
	 */
	public List<RiderOnlineData> getResultsByRaceNumber(Category category, int year, int raceNumber, Session session){
		return data.getResultsByRaceNumber(category, year, raceNumber, session);
	}

	/**
	 * If the standings are not available, an empty List will be returned.
	 * @param category
	 * @param year
	 * @return
	 */
	public List<RiderStandingsData> getChampionshipStandings(Category category, int year){
		return data.getChampionshipStandings(category, year);
	}

	/**
	 * If the results are not available or the session does not exist, an empty List will be returned.
	 * @param category
	 * @param year
	 * @param code
	 * @return
	 */
	@Deprecated
	List<RiderOnlineData> getFreePractice1(Category category, int year, RaceCode code){
		return data.getResultsByRaceCode(category, year, code, Session.FP1);
	}
	/**
	 * If the results are not available or the session does not exist, an empty List will be returned.
	 * @param category
	 * @param year
	 * @param code
	 * @return
	 */
	@Deprecated
	List<RiderOnlineData> getFreePractice2(Category category, int year, RaceCode code){
		return data.getResultsByRaceCode(category, year, code, Session.FP2);
	}
	/**
	 * If the results are not available or the session does not exist, an empty List will be returned.
	 * @param category
	 * @param year
	 * @param code
	 * @return
	 */
	@Deprecated
	List<RiderOnlineData> getFreePractice3(Category category, int year, RaceCode code){
		return data.getResultsByRaceCode(category, year, code, Session.FP3);
	}
	/**
	 * If the results are not available or the session does not exist, an empty List will be returned.
	 * @param category
	 * @param year
	 * @param code
	 * @return
	 */
	@Deprecated
	List<RiderOnlineData> getFreePractice4(Category category, int year, RaceCode code){
		return data.getResultsByRaceCode(category, year, code, Session.FP4);
	}
	/**
	 * If the results are not available or the session does not exist, an empty List will be returned.
	 * @param category
	 * @param year
	 * @param code
	 * @return
	 */
	@Deprecated
	List<RiderOnlineData> getQualifying1(Category category, int year, RaceCode code){
		return data.getResultsByRaceCode(category, year, code, Session.QP1);
	}
	/**
	 * If the results are not available or the session does not exist, an empty List will be returned.
	 * @param category
	 * @param year
	 * @param code
	 * @return
	 */
	@Deprecated
	List<RiderOnlineData> getQualifying2(Category category, int year, RaceCode code){
		return data.getResultsByRaceCode(category, year, code, Session.QP2);
	}
	/**
	 * If the results are not available or the session does not exist, an empty List will be returned.
	 * @param category
	 * @param year
	 * @param code
	 * @return
	 */
	@Deprecated
	List<RiderOnlineData> getQualifying(Category category, int year, RaceCode code){
		return data.getResultsByRaceCode(category, year, code, Session.QP);
	}
	/**
	 * If the results are not available or the session does not exist, an empty List will be returned.
	 * @param category
	 * @param year
	 * @param code
	 * @return
	 */
	@Deprecated
	List<RiderOnlineData> getWarmUp(Category category, int year, RaceCode code){
		return data.getResultsByRaceCode(category, year, code, Session.WARMUP);
	}
	/**
	 * If the results are not available or the session does not exist, an empty List will be returned.
	 * @param category
	 * @param year
	 * @param code
	 * @return
	 */
	@Deprecated
	public List<RiderOnlineData> getGrid(Category category, int year, RaceCode code){
		return data.getResultsByRaceCode(category, year, code, Session.GRID);
	}
	/**
	 * If the results are not available or the session does not exist, an empty List will be returned.
	 * @param category
	 * @param year
	 * @param code
	 * @return
	 */
	@Deprecated
	public List<RiderOnlineData> getRaceResults(Category category, int year, RaceCode code){
		return data.getResultsByRaceCode(category, year, code, Session.RACE);
	}


}
