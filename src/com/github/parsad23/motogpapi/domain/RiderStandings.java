package com.github.parsad23.motogpapi.domain;

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
	public RiderStandings(String name, int position, double points) {
		super(name, position, points);
	}

	@Override
	public String toString() {
		return "RiderStandingsData{" +
				"name='" + name + '\'' +
				", position=" + position +
				", points=" + points +
				'}';
	}
}
