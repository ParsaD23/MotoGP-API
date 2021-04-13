package com.github.parsad23.motogpapi.domain;

import java.util.List;

/**
 * Represents the information about a rider in the riders' standings.
 */
public class RiderStandings extends ElementStandings{

	/**
	 * Constructor of RiderStandings
	 * @param name Name of the rider
	 * @param position Position of the rider in the standings
	 * @param points Total points of the rider in the standings
	 */
	public RiderStandings(String name, int position, double points, List<Double> results) {
		super(name, position, points, results);
	}

	@Override
	public String toString() {
		return "RiderStandingsData" + super.toString();
	}
}
