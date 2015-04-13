package de.swm.example.controller;

import de.swm.example.controller.model.Timestamp;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * TODO (KS) Dokumentieren.
 *
 * @author steuer.konstantin <br>
 * copyright (C) 2015, SWM Services GmbH
 */
@RestController
public class TimpestampRestController {

	@RequestMapping(value = "/timestamp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Timestamp timestamp() {
		return new Timestamp(new Date());
	}
}
