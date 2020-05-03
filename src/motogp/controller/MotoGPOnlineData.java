package motogp.controller;

import motogp.model.*;

import java.util.List;

public interface MotoGPOnlineData {

	List<RiderOnlineData> getGrid(int year, RaceCode code);
	List<RiderOnlineData> getRaceResults(int year, RaceCode code);
}
