package com.cardfit.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan({"com.cardfit.project.controller", "com.cardfit.project.config", "com.cardfit.project.service"})
public class CardFitApplication extends SpringBootServletInitializer{

	@Override
	   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	      return application.sources(CardFitApplication.class);
	   }

	
	public static void main(String[] args) {
		SpringApplication.run(CardFitApplication.class, args);
	}

}
