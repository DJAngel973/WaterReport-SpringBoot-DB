# Use a base image with OpenJDK 17
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy your application's JAR file into the container
COPY target/demo-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port where your Spring Boot app runs
EXPOSE 8443

# Command to run the application when the container starts
ENTRYPOINT ["java", "-jar", "/app/app.jar"]