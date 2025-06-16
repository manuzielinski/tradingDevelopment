# Etapa 1: Build
FROM maven:3.9.6-eclipse-temurin-17 as build

WORKDIR /app

# Copiar solo archivos necesarios
COPY pom.xml .
COPY src ./src

# Construir el JAR
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final con JDK
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiar el JAR generado desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Puerto que usará Eureka
EXPOSE 8761

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]
