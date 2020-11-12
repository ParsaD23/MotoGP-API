package com.github.parsad23.motogpapi.domain;

/**
 * Represents the information of a rider in a session.
 */
public class RiderSession {
	private int number;
	private String name;
	private String nationality;
	private String team;
	private int position;
	private int time;
	private int laps;

	/**
	 * Constructor of RiderSession
	 * @param number Racing number of the rider
	 * @param name Name of the rider
	 * @param nationality Nationality of the rider
	 * @param team Team of the rider
	 * @param position Position of the rider in the session
	 * @param time Rider's time in milliseconds
	 * @param laps Number of completed laps
	 */
	public RiderSession(int number, String name, String nationality, String team, int position, int time, int laps) {
		this.number = number;
		this.name = name;
		this.team = team;
		this.position = position;
		this.nationality = nationality;
		this.time = time;
		this.laps = laps;
	}

	/**
	 * If the session is the race, returns the rider's total time in milliseconds to complete the race.
	 * Otherwise, returns the rider's fastest lap time in milliseconds in the session
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Returns the nationality of the rider
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * Returns the number of the rider.
	 * Returns -1 if the data is not available
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Returns the name of the rider
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the name of the rider's team
	 */
	public String getTeam() {
		return team;
	}

	/**
	 * Returns the final position of the rider.
	 * Returns 0 if the rider did not participate to the session or did not finish the race
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * Returns the total number of the completed laps in the session
	 */
	public int getLaps() {
		return laps;
	}

	@Override
	public String toString() {
		return "RiderOnlineData{" +
				"number=" + number +
				", name='" + name + '\'' +
				", nationality='" + nationality + '\'' +
				", team='" + team + '\'' +
				", position=" + position +
				", time=" + time +
				", laps=" + laps +
				'}';
	}
}
