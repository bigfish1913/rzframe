package com.rz.frame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

//@SpringBootApplication
@EnableAsync//启动异步
@EnableAutoConfiguration
@ComponentScan({"com.rz.frame.controller", "com.rz.frame.utils"})
public class SpringTomcatStart  extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringTomcatStart.class);
    }
        public static void main(String[] args) {
            System.out.println("hello");

            SpringApplication.run(SpringTomcatStart.class);
        }

}
