package com.dim.fxapp.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RegistryModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistryModuleApplication.class, args);
	}
}
