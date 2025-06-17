package com.manudev.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal para iniciar el microservicio Gateway.
 */
@SpringBootApplication
public final class MicroserviceGatewayApplication {

	private MicroserviceGatewayApplication() {
		// Suppress default constructor for a utility class
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
	}

	/**
	 * Método principal para ejecutar la aplicación Spring Boot del microservicio Gateway.
	 * @param args Argumentos de línea de comandos pasados a la aplicación.
	 */
	public static void main(final String[] args) {
		SpringApplication.run(MicroserviceGatewayApplication.class, args);
	}
}
