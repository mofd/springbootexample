package de.swm.example.web;

import de.swm.example.web.model.TimestampDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO (KS) Dokumentieren.
 *
 * @author steuer.konstantin <br>
 * copyright (C) 2015, SWM Services GmbH
 */
@RestController
@RequestMapping(value = CommonTimestamp.MAIN_PATH)
public class TimestampJsonController extends CommonTimestamp {

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public TimestampDTO create() {
		return createTimestamp();
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TimestampDTO> timestampList() {
		return getAll();
	}

}
