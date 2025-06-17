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

	public static void main(final String[] args) {
		SpringApplication.run(
				MicroserviceGatewayApplication.class, args);
	}
}
