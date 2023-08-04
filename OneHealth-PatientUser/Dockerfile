# Use a lightweight base image for Maven
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the source code into the container
COPY . .

# Build the application using Maven
RUN mvn clean install

# Use a lightweight base image for Java
FROM adoptopenjdk:17-jdk-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR from the build stage into the container
COPY --from=build /app/target/OneHealth-PatientUser-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot application is running on (change the port accordingly)
EXPOSE 8081

# Run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]
