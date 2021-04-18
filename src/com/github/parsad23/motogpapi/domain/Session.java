package com.github.parsad23.motogpapi.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the sessions
 */
public enum Session {
	FP1("1st practice", "free practice 1", "1st free practice"),
	FP2("2nd practice", "free practice 2", "2nd free practice"),
	FP3("3rd practice", "free practice 3", "3rd free practice"),
	FP4("4th practice", "free practice 4", "4th free practice"),
	QP1("1st qualifying", "qualifying 1"),
	QP2("2nd qualifying", "qualifying 2"),
	QP("qualifying", "combined qualifying"),
	GRID("grid"),
	WARMUP("warm Up", "warm-up"),
	RACE("race");

	private String value1 = "";
	private String value2 = "";
	private String value3 = "";

	Session(String value1) {
		this.value1 = value1;
	}
	Session(String value1, String value2) {
		this.value1 = value1; this.value2 = value2;
	}
	Session(String value1, String value2, String value3) {
		this.value1 = value1; this.value2 = value2; this.value3 = value3;
	}

	public List<String> getValues(){
		List<String> values = new ArrayList<>();
		values.add(value1);
		values.add(value2);
		values.add(value3);
		return values;
	}
}

