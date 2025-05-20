
# 💹 tradingDevelopment

Arquitectura de microservicios para una aplicación de trading distribuida, desarrollada en Java utilizando el ecosistema Spring. Este repositorio contiene la implementación modular y escalable de distintos servicios financieros, con foco en mantenibilidad, calidad de código e integración continua.

---

## 📦 Estructura del Repositorio

```
tradingDevelopment/
├── commons/               # Módulo con DTOs, enums y utilidades compartidas
├── microservices/         # Microservicios individuales
│   ├── coin-service/      
│   ├── user-service/      
│   ├── wallet-service/    
│   ├── order-service/     
│   ├── payment-service/   
│   └── ...
├── .github/workflows/     # Pipelines de CI
├── .mvn/                  # Configuración de Maven Wrapper
├── mvnw, mvnw.cmd         # Ejecutables de Maven Wrapper
├── pom.xml                # POM raíz
└── README.md              # Este archivo
```

---

## 🧰 Tecnologías y Herramientas

| Tecnología              | Propósito                                                        |
|------------------------|-------------------------------------------------------------------|
| Java 17+               | Lenguaje de programación principal                                |
| Spring Boot 3.x        | Base de los microservicios                                        |
| Spring Cloud           | Eureka, Config Server, Gateway                                    |
| Spring Data JPA        | Acceso a base de datos y ORM                                      |
| Spring Security        | Autenticación y autorización                                      |
| Spring Web             | Construcción de APIs REST                                         |
| Feign Clients          | Comunicación entre microservicios                                 |
| JWT                    | Autenticación sin estado                                          |
| MapStruct              | Mapeo entre entidades y DTOs                                      |
| Lombok                 | Reducción de código repetitivo                                    |
| Maven                  | Gestión de dependencias y build                                   |
| JUnit 5                | Testing                                                           |
| Checkstyle             | Control de calidad del código                                     |
| Docker (opcional)      | Contenedores                                                      |
| GitHub Actions         | Integración continua (CI)                                         |

---

## ⚙️ Requisitos Previos

- JDK 17 o superior  
- Maven 3.8 o superior  
- Git  
- (Opcional) Docker + Docker Compose  
- (Opcional) IDE: IntelliJ IDEA o VS Code  

---

## 🚀 Ejecución del Proyecto

### 1. Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/tradingDevelopment.git
cd tradingDevelopment
```

### 2. Instalar módulo `commons` (necesario para otros servicios)

```bash
mvn clean install -pl commons -am
```

### 3. Ejecutar un microservicio (ejemplo: `coin-service`)

```bash
cd microservices/coin-service
mvn spring-boot:run
```

---

## 🧩 Microservicios Principales

| Servicio                 | Puerto | Responsabilidad Principal                     | Integraciones                 |
|--------------------------|--------|-----------------------------------------------|-------------------------------|
| User Service             | 8086   | Autenticación y gestión de perfiles           | -                             |
| Order Service            | 8088   | Gestión de órdenes de trading                 | User, Coin, Wallet Services   |
| Wallet Service           | 8085   | Billeteras, balances y activos                | User Service                  |
| Coin Service             | 8089   | Información de criptomonedas                  | CoinGecko API                 |
| Payment Gateway Service  | 8087   | Procesamiento de pagos                        | Stripe, Razorpay, User Service|

---

## 🗃️ Gestión de Bases de Datos

Cada servicio mantiene su propia base de datos (`Database-per-Service`):

| Servicio                 | Base de Datos                |
|--------------------------|------------------------------|
| User Service             | `user_service_database`      |
| Order Service            | `order_service_database`     |
| Wallet Service           | `wallet_service_database`    |
| Coin Service             | `coin_service_database`      |
| Payment Service          | `payment_gateway_database`   |

---

## 🧱 Infraestructura

| Componente                        | Propósito                                         |
|----------------------------------|---------------------------------------------------|
| Spring Cloud Netflix Eureka      | Descubrimiento de servicios                       |
| Spring Cloud Config Server       | Configuración centralizada                        |
| Spring Cloud Gateway             | Enrutamiento de solicitudes                       |
| Spring Cloud OpenFeign           | Cliente REST declarativo                          |
| Spring Validation                | Validación de datos con Jakarta Bean Validation   |
| Spring Data JDBC (opcional)     | Acceso directo a SQL cuando es necesario          |

---

## 🌐 Integraciones Externas

| Integración       | Propósito                                 |
|-------------------|--------------------------------------------|
| CoinGecko API     | Obtener precios y datos del mercado cripto|
| Stripe            | Procesamiento de pagos                    |
| Razorpay          | Alternativa de pasarela de pagos          |

---

## 🧪 Testing y Calidad

| Herramienta             | Propósito                                      |
|--------------------------|-----------------------------------------------|
| Spring Boot Test         | Pruebas de unidad e integración               |
| Spring Security Test     | Test de endpoints protegidos                  |
| Checkstyle               | Validación de estilo                         |
| H2 Database              | Base de datos en memoria para pruebas         |

---

## 🔄 Integración Continua (CI)

Este proyecto utiliza **GitHub Actions** para CI/CD.

📁 Configuración del pipeline:  
`.github/workflows/ci.yml`

### El pipeline realiza:

- Clonación del repositorio  
- Instalación de Java 17  
- Compilación del módulo `commons`  
- Validación con **Checkstyle**  
- Compilación del servicio `coin-service`  
- Ejecución de pruebas unitarias  
- Generación de reportes de cobertura  
