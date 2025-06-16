# Usa JDK 17 como base
FROM eclipse-temurin:17-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR generado por Maven
COPY target/eureka-service-*.jar app.jar

# Exponer el puerto de Eureka
EXPOSE 8761

# Ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
