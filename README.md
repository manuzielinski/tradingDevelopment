💹 tradingDevelopment

Arquitectura de microservicios para una aplicación de trading distribuida, desarrollada en Java utilizando el ecosistema Spring. Este repositorio contiene la implementación modular y escalable de distintos servicios financieros, con foco en mantenibilidad, calidad de código e integración continua.
📦 Estructura del Repositorio

tradingDevelopment/
├── commons/               # Módulo con DTOs, enums y utilidades compartidas
├── microservices/         # Contiene los microservicios individuales
│   ├── coin-service/      # Servicio que interactúa con CoinGecko
│   ├── user-service/      # Servicio de usuarios
│   ├── wallet-service/    # Servicio de billeteras
│   ├── order-service/     # Servicio de órdenes de trading
│   ├── payment-service/   # Servicio de pasarela de pagos
│   └── ...                # Otros microservicios planificados
├── .github/workflows/     # Pipelines de CI con GitHub Actions
├── .mvn/                  # Archivos de configuración de Maven Wrapper
├── mvnw, mvnw.cmd         # Maven Wrapper
├── pom.xml                # POM raíz con dependencias comunes
└── README.md              # Este archivo

🧰 Tecnologías y Herramientas
Tecnología	Propósito
Java 17+	Lenguaje de programación principal
Spring Boot 3.x	Base para todos los microservicios
Spring Cloud	Eureka, Config Server, Gateway, descubrimiento y configuración
Spring Data JPA	Acceso a base de datos y ORM
Spring Security	Autenticación y autorización
Feign Clients	Comunicación entre microservicios (cliente REST declarativo)
JWT	Autenticación sin estado
MapStruct	Mapeo entre entidades y DTOs
Lombok	Reducción de código repetitivo
Maven 3.8+	Gestión de dependencias y construcción
JUnit 5	Pruebas unitarias
Checkstyle	Control de calidad de código
Docker	Contenedores (opcional)
GitHub Actions	Integración continua
⚙️ Requisitos Previos

    JDK 17 o superior

    Maven 3.8 o superior

    Git

    (Opcional) Docker + Docker Compose

    (Opcional) IDE como IntelliJ IDEA o VS Code

🚀 Ejecución del Proyecto
1. Clonar el repositorio

git clone https://github.com/tu-usuario/tradingDevelopment.git
cd tradingDevelopment

2. Instalar el módulo commons (requerido por los demás servicios)

mvn clean install -pl commons -am

3. Ejecutar un microservicio (ejemplo: coin-service)

cd microservices/coin-service
mvn spring-boot:run

🧩 Microservicios Principales
Servicio	Puerto	Responsabilidad Principal	Integraciones Clave
User Service	8086	Gestión de usuarios, autenticación y perfiles	-
Order Service	8088	Creación y gestión de órdenes de trading	User, Coin, Wallet Services
Wallet Service	8085	Administración de billeteras, balances y activos	User Service
Coin Service	8089	Gestión de criptomonedas	CoinGecko API
Payment Gateway Service	8087	Procesamiento de pagos	Stripe, Razorpay, User Service
🗃️ Gestión de Datos

Cada microservicio posee su propia base de datos siguiendo el patrón database-per-service:
Servicio	Base de Datos
User Service	user_service_database
Order Service	order_service_database
Wallet Service	wallet_service_database
Coin Service	coin_service_database
Payment Gateway Service	payment_gateway_database
🧱 Infraestructura y Frameworks
Componente	Propósito
Spring Cloud Netflix Eureka	Registro y descubrimiento de servicios
Spring Cloud OpenFeign	Comunicación REST entre microservicios
Spring Gateway	Enrutamiento centralizado de solicitudes
Config Server	Gestión centralizada de configuración
🗄️ Persistencia de Datos
Tecnología	Propósito
MySQL	Base de datos principal en producción
H2 Database	Base de datos en memoria para desarrollo y pruebas
Hibernate	Implementación ORM (parte de Spring Data JPA)
Jakarta Persistence API	API estándar para mapeo objeto-relacional
🌐 Integraciones Externas
Integración	Propósito
CoinGecko API	Obtener precios y datos del mercado cripto
Stripe	Procesamiento de pagos
Razorpay	Alternativa de pasarela de pagos
🧪 Testing y Calidad
Herramienta	Propósito
Spring Boot Test	Soporte para pruebas de integración y unidad
Spring Security Test	Pruebas sobre endpoints autenticados
Checkstyle	Validación del estilo y consistencia del código
H2 Database	Base en memoria para pruebas aisladas
🔄 Integración Continua (CI)

Este proyecto incluye pipelines automáticos configurados con GitHub Actions, que se ejecutan en cada push o pull request sobre las ramas main o develop.
📄 Archivo de configuración del pipeline:

.github/workflows/ci.yml

¿Qué valida el pipeline?

    Clonación del proyecto

    Instalación de Java 17

    Compilación del módulo commons

    Validación de estilo con Checkstyle

    Compilación del servicio coin-service

    Ejecución de pruebas unitarias

    Generación de informes de cobertura
