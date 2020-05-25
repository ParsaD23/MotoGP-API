package motogp.controller.motogpdata;

import motogp.controller.motorsportstats.MotorsportOnlineData;
import motogp.model.RaceCode;
import motogp.model.RiderOnlineData;
import motogp.model.Category;
import motogp.model.Session;

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
	 * @param code
	 * @return
	 */
	List<RiderOnlineData> getFreePractice1(Category category, int year, RaceCode code){
		return data.getResults(category, year, code, Session.FP1);
	}
	/**
	 * If the results are not available or the session does not exist, an empty List will be returned.
	 * @param category
	 * @param year
	 * @param code
	 * @return
	 */
	List<RiderOnlineData> getFreePractice2(Category category, int year, RaceCode code){
		return data.getResults(category, year, code, Session.FP2);
	}
	/**
	 * If the results are not available or the session does not exist, an empty List will be returned.
	 * @param category
	 * @param year
	 * @param code
	 * @return
	 */
	List<RiderOnlineData> getFreePractice3(Category category, int year, RaceCode code){
		return data.getResults(category, year, code, Session.FP3);
	}
	/**
	 * If the results are not available or the session does not exist, an empty List will be returned.
	 * @param category
	 * @param year
	 * @param code
	 * @return
	 */
	List<RiderOnlineData> getFreePractice4(Category category, int year, RaceCode code){
		return data.getResults(category, year, code, Session.FP4);
	}
	/**
	 * If the results are not available or the session does not exist, an empty List will be returned.
	 * @param category
	 * @param year
	 * @param code
	 * @return
	 */
	List<RiderOnlineData> getQualifying1(Category category, int year, RaceCode code){
		return data.getResults(category, year, code, Session.QP1);
	}
	/**
	 * If the results are not available or the session does not exist, an empty List will be returned.
	 * @param category
	 * @param year
	 * @param code
	 * @return
	 */
	List<RiderOnlineData> getQualifying2(Category category, int year, RaceCode code){
		return data.getResults(category, year, code, Session.QP2);
	}
	/**
	 * If the results are not available or the session does not exist, an empty List will be returned.
	 * @param category
	 * @param year
	 * @param code
	 * @return
	 */
	List<RiderOnlineData> getQualifying(Category category, int year, RaceCode code){
		return data.getResults(category, year, code, Session.QP);
	}
	/**
	 * If the results are not available or the session does not exist, an empty List will be returned.
	 * @param category
	 * @param year
	 * @param code
	 * @return
	 */
	List<RiderOnlineData> getWarmUp(Category category, int year, RaceCode code){
		return data.getResults(category, year, code, Session.WARMUP);
	}
	/**
	 * If the results are not available or the session does not exist, an empty List will be returned.
	 * @param category
	 * @param year
	 * @param code
	 * @return
	 */
	public List<RiderOnlineData> getGrid(Category category, int year, RaceCode code){
		return data.getResults(category, year, code, Session.GRID);
	}
	/**
	 * If the results are not available or the session does not exist, an empty List will be returned.
	 * @param category
	 * @param year
	 * @param code
	 * @return
	 */
	public List<RiderOnlineData> getRaceResults(Category category, int year, RaceCode code){
		return data.getResults(category, year, code, Session.RACE);
	}
}
