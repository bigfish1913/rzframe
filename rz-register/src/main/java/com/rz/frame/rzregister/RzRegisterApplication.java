package com.rz.frame.rzregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RzRegisterApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RzRegisterApplication.class, args);
	}
	
}
