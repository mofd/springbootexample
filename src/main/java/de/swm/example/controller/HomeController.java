package de.swm.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO (KS) Dokumentieren.
 *
 * @author steuer.konstantin <br>
 * copyright (C) 2015, SWM Services GmbH
 */
@Controller
public class HomeController {

	@RequestMapping("/")
	public String index(){
		return "index";
	}

	@RequestMapping("/index.html")
	public String indexHtml(){
		return "index";
	}
}
