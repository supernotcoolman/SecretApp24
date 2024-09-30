package com.example.secretapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan ("com.example.secretapp.entity")
@SpringBootApplication
public class SecretAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(SecretAppApplication.class, args);
	}

}
