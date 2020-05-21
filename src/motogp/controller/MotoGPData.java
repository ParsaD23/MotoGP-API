package motogp.controller;

import motogp.model.RaceCode;
import motogp.model.RiderOnlineData;

import java.util.List;


public class MotoGPData {

	private MotoGPOnlineData data;

	public MotoGPData(){
		data = new MotorsportOnlineData();
	}

	public List<RiderOnlineData> getGrid(int year, RaceCode code){
		return data.getGrid(year, code);
	}
	public List<RiderOnlineData> getRaceResults(int year, RaceCode code){
		return data.getRaceResults(year, code);
	}
}
