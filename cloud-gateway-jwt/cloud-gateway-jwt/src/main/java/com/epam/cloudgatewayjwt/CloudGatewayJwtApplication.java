package com.epam.cloudgatewayjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CloudGatewayJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayJwtApplication.class, args);
	}

}
