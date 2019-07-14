package com.rz.frame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;


//@SpringBootApplication
@EnableAsync//启动异步
@EnableAutoConfiguration
 @ComponentScan(
 		{"com.rz.frame.controller"})
public class RzAdminBoot {
	public static void main(String[] args) {
		System.out.println("hello");
		SpringApplication.run(RzAdminBoot.class);
	}
}
