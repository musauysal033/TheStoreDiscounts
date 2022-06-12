package com.example.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.example"})
@EnableJpaRepositories(basePackages = {"com.example"})
@EntityScan(basePackages = {"com.example"})
@ComponentScan(basePackages = {"com.example"})
public class TheStoreDiscountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheStoreDiscountsApplication.class, args);
	}

}
