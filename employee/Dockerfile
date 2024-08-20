# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from your build directory into the container
COPY target/employee-0.0.1-SNAPSHOT.jar /app/employee-0.0.1-SNAPSHOT.jar

# Expose the port your microservice runs on
EXPOSE 8001

# Define the command to run your application
CMD ["java", "-jar", "employee-0.0.1-SNAPSHOT.jar"]
