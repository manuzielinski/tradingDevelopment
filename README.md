# tradingDevelopment

Arquitectura de microservicios para una aplicación de trading distribuida, desarrollada en Java utilizando el ecosistema Spring. Este repositorio contiene la implementación modular de distintos servicios que componen un sistema financiero escalable y mantenible.

## 📦 Estructura del Repositorio

```
tradingDevelopment/
├── commons/             # Módulo con clases utilitarias y configuraciones compartidas
├── microservices/       # Contiene microservicios individuales (usuarios, operaciones, etc.)
│   ├── service-name/    # Cada microservicio es un proyecto Spring Boot independiente
├── .mvn/                # Archivos de configuración de Maven Wrapper
├── mvnw, mvnw.cmd       # Wrappers de Maven
├── pom.xml              # POM raíz con gestión de dependencias y módulos
└── README.md            # Documentación del proyecto
```

## 🧰 Tecnologías y Herramientas

- **Java 17**
- **Spring Boot 3.x**
- **Spring Cloud (configuración, descubrimiento, gateway)**
- **Maven 3.8+**
- **Docker (opcional para despliegue)**
- **Eureka Server (descubrimiento de servicios)**
- **Config Server (gestión externa de configuración)**

## ⚙️ Requisitos Previos

- JDK 17 o superior
- Maven 3.8 o superior
- Git
- (Opcional) Docker y Docker Compose
- (Opcional) IntelliJ IDEA o VS Code para desarrollo asistido

## 📚 Notas Técnicas

- Todos los servicios implementan patrones de diseño orientados a microservicios: descubrimiento (Eureka), configuración centralizada, gateway, etc.
- El módulo `commons` contiene DTOs, excepciones, y clases utilitarias compartidas entre servicios.
- La comunicación entre servicios puede ser sincrónica (REST) o asincrónica (mediante colas si se agrega RabbitMQ/Kafka).
- El proyecto sigue una arquitectura hexagonal, separando claramente capas de infraestructura, dominio y aplicación.

