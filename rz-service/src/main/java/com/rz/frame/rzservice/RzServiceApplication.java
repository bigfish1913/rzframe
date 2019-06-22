package com.rz.frame.rzservice;

import com.rz.frame.contract.IRzSerivce;
import com.rz.frame.core.RegisterSerivce;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.rz.frame")
@SpringBootApplication
public class RzServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RzServiceApplication.class, args);
		RegisterSerivce.registerService(RzMiniService.class);
	}

}
