package edu.eci.cvds.UserManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * UserManagementApplication is the main entry point for the User Management
 * application. It bootstraps the application using Spring Boot's autoconfiguration.
 */
@SpringBootApplication
public class UserManagementApplication {

	/**
	 * Main method that launches the Spring Boot application.
	 *
	 * @param args command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

}
