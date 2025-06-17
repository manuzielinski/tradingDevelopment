package com.manudev.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal para iniciar el microservicio Gateway.
 */
@SpringBootApplication
public final class MicroserviceGatewayApplication {
	    /**
	 * Constructor privado para evitar la instanciación de la clase.
	 */
	private MicroserviceGatewayApplication() {
		throw new UnsupportedOperationException("Clase de utilidad");
	}

	/**
	 * Punto de entrada principal de la aplicación.
	 *
	 * @param args los argumentos de línea de comandos
	 */
	public static void main(final String[] args) {
		SpringApplication.run(
				MicroserviceGatewayApplication.class, args);
	}
}
