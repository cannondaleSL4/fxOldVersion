package com.dim.fxapp.request;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class RequestModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequestModuleApplication.class, args);
	}
}
