package com.example.CRUD_Spring_Boot;

import com.example.CRUD_Spring_Boot.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class CrudSpringBootApplication {

	public static void main(String[] args) {

		SpringApplication.run(CrudSpringBootApplication.class, args);



	}

}
