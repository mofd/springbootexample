package de.swm.example.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * TODO (KS) Dokumentieren.
 *
 * @author steuer.konstantin <br>
 * copyright (C) 2015, SWM Services GmbH
 */
@Controller
public class TimpestampWebController {

	@RequestMapping(value = "/timestamp", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String timestemp(Model model) {
		model.addAttribute("timestampValue", new Date().toString());
		return "timestamp";
	}
}
