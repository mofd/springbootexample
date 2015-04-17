package de.swm.example.web;

import de.swm.example.Application;
import de.swm.example.persistence.domain.Timestamp;
import de.swm.example.persistence.repository.TimestampRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * TODO (KS) Dokumentieren.
 *
 * @author steuer.konstantin <br>
 * copyright (C) 2015, SWM Services GmbH
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest({ "server.port=0" })
@TransactionConfiguration(defaultRollback = true)
public class TimestampWebControllerTest {

	public static final String HTTP_LOCALHOST_RANDOM_PORT_PATTERN = "http://foo:bar@localhost:%d/index.html";
	RestTemplate template = new TestRestTemplate();

	@Autowired
	private TimestampRepository timestampRepository;

	@Value("${local.server.port}")
	int randomPort;

	@Before
	public void setUp() throws Exception {
		for (int index = 0; index < 10; index++) {
			timestampRepository.saveAndFlush(new Timestamp(new Date()));
			Thread.sleep(1);
		}

		System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox 26\\firefox.exe");
	}

	@After
	public void tearDown() throws Exception {
		timestampRepository.deleteAll();
	}

	@Test
	public void testTimestamp() throws Exception {
		WebDriver webDriver = null;
		try {
			webDriver = new FirefoxDriver();
		} catch (ExceptionInInitializerError e) {
			return;
		}

		webDriver.get(getUrl());
		WebElement createNewTimestamp = webDriver.findElement(By.id("createNewTimestampForm"));
		createNewTimestamp.submit();

		new WebDriverWait(webDriver, 5).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver webDriver) {
				System.out.print(webDriver.getPageSource());
				WebElement timestamp = webDriver.findElement(By.id("timestamp"));
				return timestamp != null && timestamp.getTagName().equals("p") && timestamp.getText()
					.contains("Timestamp (");
			}
		});

		System.out.print(webDriver.getPageSource());
		webDriver.quit();
		assertEquals(11, timestampRepository.findAll().size());
	}

	private String getUrl() {
		return String.format(HTTP_LOCALHOST_RANDOM_PORT_PATTERN, randomPort);
	}
}
