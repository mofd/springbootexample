package de.swm.example.web;

import de.swm.example.Application;
import de.swm.example.persistence.domain.Timestamp;
import de.swm.example.persistence.repository.TimestampRepository;
import de.swm.example.web.model.TimestampDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
public class TimestampJsonControllerTest {

	public static final String HTTP_LOCALHOST_RANDOM_PORT_PATTERN = "http://localhost:%d/timestamps";
	TestRestTemplate template = new TestRestTemplate();

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

		ClientHttpRequestInterceptor securityInterceptor = new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
				HttpHeaders headers = request.getHeaders();
				headers.add("Authorization", "Basic " + Base64.getEncoder().encodeToString("foo:bar".getBytes()));
				return execution.execute(request, body);
			}
		};
		template.setInterceptors(new ArrayList<ClientHttpRequestInterceptor>() {{
			this.add(securityInterceptor);
		}});
	}

	@After
	public void tearDown() throws Exception {
		timestampRepository.deleteAll();

	}

	@Test
	public void testTimestamp() throws Exception {
		ResponseEntity<TimestampDTO> response = template.postForEntity(getUrl(), null, TimestampDTO.class);

		assertNotNull(response.getBody());
		assertNotNull(response.getBody().getId());
		assertNotNull(response.getBody().getTimestampValue());

		assertEquals(11, timestampRepository.findAll().size());
	}

	@Test
	public void testTimestampList() throws Exception {
		ResponseEntity<TimestampDTO[]> response = template.getForEntity(getUrl(), TimestampDTO[].class);

		assertNotNull(response);
		assertEquals(10, response.getBody().length);
	}

	private String getUrl() {
		return String.format(HTTP_LOCALHOST_RANDOM_PORT_PATTERN, randomPort);
	}
}
