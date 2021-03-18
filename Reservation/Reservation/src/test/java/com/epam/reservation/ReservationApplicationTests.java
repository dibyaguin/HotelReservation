package com.epam.reservation;

import com.epam.reservation.controller.ReservationController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

//@SpringBootTest
//class ReservationApplicationTests {
//
//	@Test
//	void contextLoads() {
//	}
//
//}

//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
class ReservationApplicationTests {

	@Autowired
	private ReservationController reservationController;

	@BeforeEach
	public void setup() {
		StandaloneMockMvcBuilder standaloneMockMvcBuilder
				= MockMvcBuilders.standaloneSetup(reservationController);
		RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
	}
}