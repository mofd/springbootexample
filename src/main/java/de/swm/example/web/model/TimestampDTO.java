package de.swm.example.web.model;

import java.util.Date;

/**
 * TODO (KS) Dokumentieren.
 *
 * @author steuer.konstantin <br>
 * copyright (C) 2015, SWM Services GmbH
 */
public class TimestampDTO {

	private Long id;

	private String timestampValue;

	public TimestampDTO() {
	}

	public TimestampDTO(Long id, Date currentDate) {
		this.timestampValue = currentDate.toString();
		this.id = id;
	}

	public String getTimestampValue() {
		return timestampValue;
	}

	public void setTimestampValue(String timestampValue) {
		this.timestampValue = timestampValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
