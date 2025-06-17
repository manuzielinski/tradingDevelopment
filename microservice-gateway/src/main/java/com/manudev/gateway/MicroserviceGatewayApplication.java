package com.manudev.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal para iniciar el microservicio Gateway.
 */
@SpringBootApplication
public final class MicroserviceGatewayApplication {

	private MicroserviceGatewayApplication() {
		throw new UnsupportedOperationException("Clase de utilidad");
	}

	/**
	 * Método principal para ejecutar la aplicación Spring Boot del microservicio Gateway.
	 * @param args Argumentos de línea de comandos.
	 */
	public static void main(final String[] args) {
		SpringApplication.run(
				MicroserviceGatewayApplication.class, args);
	}
}