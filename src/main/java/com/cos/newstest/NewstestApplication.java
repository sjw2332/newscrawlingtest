package com.cos.newstest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class NewstestApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewstestApplication.class, args);
	}

}
