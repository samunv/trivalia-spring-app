
# Imagen con Java 23
FROM openjdk:23-jdk-slim

# Definir el Archivo JAR compilado en Target
ARG JAR_FILE=target/trivalia-0.0.1-SNAPSHOT.jar 

# Copiar el JAR como app_trivalia.jar para simplificar
COPY ${JAR_FILE} app_trivalia.jar

# Puerto que usará la app
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app_trivalia.jar"]
