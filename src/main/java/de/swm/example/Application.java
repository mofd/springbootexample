package de.swm.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * TODO (KS) Dokumentieren.
 *
 * @author steuer.konstantin <br>
 * copyright (C) 2015, SWM Services GmbH
 */
@SpringBootApplication
@EnableWebMvc
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
