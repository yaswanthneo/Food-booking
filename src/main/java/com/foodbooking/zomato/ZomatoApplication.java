package com.foodbooking.zomato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZomatoApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(ZomatoApplication.class, args);
		System.out.println("Connected and up for running!!!");
	}

}
