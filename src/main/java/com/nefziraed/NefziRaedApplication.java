package com.nefziraed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NefziRaedApplication {

	public static void main(String[] args) {
		SpringApplication.run(NefziRaedApplication.class, args);
	}

}
