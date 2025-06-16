# Etapa 1: Build del JAR con Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml ./
COPY microservice-eureka ./microservice-eureka

# Compilamos el submódulo
RUN mvn -f microservice-eureka/pom.xml clean package -DskipTests

# Etapa 2: Imagen final
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/microservice-eureka/target/microservice-eureka-*.jar app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "app.jar"]
