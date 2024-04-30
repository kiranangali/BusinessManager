package com.remodelAi.Business_Manger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.rmodelAi.Business_Manger.Repository")
public class BusinessMangerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessMangerApplication.class, args);
	}

}
