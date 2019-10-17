package com.cardfit.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan({"com.cardfit.project.controller", "com.cardfit.project.config", "com.cardfit.project.service"})
public class CardFitApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardFitApplication.class, args);
	}

}
