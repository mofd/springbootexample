package de.swm.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * TODO (KS) Dokumentieren.
 *
 * @author steuer.konstantin <br>
 * copyright (C) 2015, SWM Services GmbH
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
