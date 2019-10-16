package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration (exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
@ComponentScan("cardfit.controller")
public class CardFitApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardFitApplication.class, args);
		
	}

}
