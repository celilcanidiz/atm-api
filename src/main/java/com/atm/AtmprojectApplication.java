package com.atm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AtmprojectApplication {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AtmprojectApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(AtmprojectApplication.class, args);
	}

}
