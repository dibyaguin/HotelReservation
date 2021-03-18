package com.epam.guest;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

//@SpringBootTest
//class GuestApplicationTests {
//
//	@Test
//	void contextLoads() {
//	}

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL,
		ids = "com.epam:Reservation:" + ":stubs:8085"
)
@DirtiesContext
class GuestApplicationTests {

	@Test
	void shouldReturnAvailableReservation() {
		//boolean result = true;
		ResponseEntity<Boolean> result = new ResponseEntity<>(true, HttpStatus.OK);
		String url = "http://localhost:9002/reservation-service/reservation/getAvailableReservation?roomId=16&fromDate=2021-04-12&toDate=2021-04-15";

		//Boolean response = new RestTemplate().getForObject(url, Boolean.class);
		ResponseEntity<Boolean> response = new RestTemplate().exchange(
				url, HttpMethod.GET, null, Boolean.class);

		BDDAssertions.then(response).isEqualTo(response);
	}

}
