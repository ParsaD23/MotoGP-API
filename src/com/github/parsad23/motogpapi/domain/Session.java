package com.github.parsad23.motogpapi.domain;

/**
 * Represents the sessions
 */
public enum Session {
	FP1("1st Practice"),
	FP2("2nd Practice"),
	FP3("3rd Practice"),
	FP4("4th Practice"),
	QP1("1st Qualifying"),
	QP2("2nd Qualifying"),
	QP("Qualifying"),
	GRID("Grid"),
	WARMUP("Warm Up"),
	RACE("Race");

	private String value;
	Session(String value) {
		this.value = value;
	}

	public String getValue(){
		return value;
	}
}

