# Use an official Maven runtime as a parent image
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . .

# Build the application using Maven
RUN mvn clean package -DskipTests

# Define the command to run the application
CMD ["java", "-jar", "target/desafio-like-0.0.1-SNAPSHOT.jar"]