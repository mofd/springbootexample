package de.swm.example.controller.model;

import java.util.Date;

/**
 * TODO (KS) Dokumentieren.
 *
 * @author steuer.konstantin <br>
 * copyright (C) 2015, SWM Services GmbH
 */
public class Timestamp {
	private String timestampValue;

	public Timestamp(Date currentDate) {
		this.timestampValue = currentDate.toString();
	}

	public String getTimestampValue() {
		return timestampValue;
	}

	public void setTimestampValue(String timestampValue) {
		this.timestampValue = timestampValue;
	}
}
