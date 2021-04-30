package com.github.parsad23.motogpapi.domain;

import java.util.Arrays;
import java.util.List;

/**
 * Represents the sessions
 */
public enum Session {
	FP1(new String[]{"1st practice", "free practice 1", "1st free practice", "practice 1"}),
	FP2(new String[]{"2nd practice", "free practice 2", "2nd free practice", "practice 2"}),
	FP3(new String[]{"3rd practice", "free practice 3", "3rd free practice", "practice 3"}),
	FP4(new String[]{"4th practice", "free practice 4", "4th free practice", "practice 4"}),
	QP1(new String[]{"1st qualifying", "qualifying 1"}),
	QP2(new String[]{"2nd qualifying", "qualifying 2"}),
	QP(new String[]{"qualifying", "combined qualifying"}),
	GRID(new String[]{"grid"}),
	WARMUP(new String[]{"warm Up", "warm-up"}),
	RACE(new String[]{"race"});

	private String[] values;

	Session(String[] values) { this.values = values; }

	public List<String> getValues(){
		return Arrays.asList(values);
	}
}

