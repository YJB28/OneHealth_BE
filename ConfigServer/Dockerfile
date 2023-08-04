# Use a lightweight base image for Maven
FROM maven:3.8.3-openjdk-17-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the source code into the container
COPY . .

# Build the application using Maven
RUN mvn clean install

# Use a lightweight base image for Java
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Verify the files in the target directory
RUN ls 

# Copy the compiled JAR from the build stage into the container
COPY target/ConfigServer-0.0.1-SNAPSHOT.jar app.jar

RUN ls

# Expose the port your Spring Boot application is running on (change the port accordingly)
EXPOSE 8888

# Run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]
