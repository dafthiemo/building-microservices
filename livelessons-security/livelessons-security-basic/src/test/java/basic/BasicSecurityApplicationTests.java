package basic;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BasicSecurityApplicationTests {

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	@LocalServerPort
	private int port;

	@Test
	public void contextLoaded() throws Throwable {
		RestTemplate restTemplate = this.restTemplateBuilder
				.basicAuthorization("pwebb", "boot").build();
		ResponseEntity<String> response = restTemplate
				.getForEntity("http://localhost:" + this.port + "/hi", String.class);
		String actual = response.getBody().trim().toLowerCase();
		Assert.assertTrue(actual.contains("hello"));
		System.out.println("received: " + actual);
	}

}
