package de.swm.example.web;

import de.swm.example.persistence.domain.Timestamp;
import de.swm.example.persistence.repository.TimestampRepository;
import de.swm.example.web.model.TimestampDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * TODO (KS) Dokumentieren.
 *
 * @author steuer.konstantin <br>
 * copyright (C) 2015, SWM Services GmbH
 */
public abstract class CommonTimestamp {

	public static final String MAIN_PATH = "timestamps";
	@Autowired
	private TimestampRepository timestampRepository;

	protected TimestampDTO createTimestamp() {
		Date date = new Date();
		Timestamp created = timestampRepository.saveAndFlush(new Timestamp(date));
		return new TimestampDTO(created.getId(), date);
	}

	protected List<TimestampDTO> getAll() {
		List<Timestamp> all = timestampRepository.findAll();
		return all.stream()
			.map(timestamp -> new TimestampDTO(timestamp.getId(), timestamp.getTimestamp()))
			.collect(Collectors.toList());
	}

	protected TimestampDTO findById(Long id) {
		Timestamp found = timestampRepository.findOne(id);
		return new TimestampDTO(found.getId(), found.getTimestamp());
	}
}
