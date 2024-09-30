package com.example.Migradordatos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MigradordatosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MigradordatosApplication.class, args);
	}

}
