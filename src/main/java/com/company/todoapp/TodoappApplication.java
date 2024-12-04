package com.company.todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@SecurityScheme(name = "TodoApp", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)

public class TodoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoappApplication.class, args);
	}

}
