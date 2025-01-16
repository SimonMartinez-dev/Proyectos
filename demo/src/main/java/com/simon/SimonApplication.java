package com.simon;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.simon")
@EnableJpaRepositories("com.simon.repositories")
@EntityScan("com.simon.models")
public class SimonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimonApplication.class, args);
	}

}
