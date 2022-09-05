package com.example.mimir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MimirApplication {

	public static void main(String[] args) {
		SpringApplication.run(MimirApplication.class, args);
	}
}
