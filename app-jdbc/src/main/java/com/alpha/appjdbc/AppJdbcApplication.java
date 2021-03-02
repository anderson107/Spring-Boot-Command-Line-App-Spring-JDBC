package com.alpha.appjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppJdbcApplication {
	
	public AppJdbcApplication() {
		new Application().displayMenu();
	}

	public static void main(String[] args) {
		SpringApplication.run(AppJdbcApplication.class, args);
	}

}
