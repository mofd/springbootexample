package de.swm.example;

import de.swm.example.persistence.repository.TimestampRepository;
import de.swm.example.web.TimestampJsonController;
import de.swm.example.web.TimestampWebController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * TODO (KS) Dokumentieren.
 *
 * @author steuer.konstantin <br>
 * copyright (C) 2015, SWM Services GmbH
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationTest {

	@Autowired
	private TimestampRepository timestampRepository;

	@Autowired
	private TimestampWebController timestampWebController;

	@Autowired
	private TimestampJsonController timestampJsonController;

	@Test
	public void testApplicationContext(){
		assertNotNull(timestampRepository);
		assertNotNull(timestampWebController);
		assertNotNull(timestampJsonController);
	}
}
