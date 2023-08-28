# Use the official maven/Java 17 image to create a build artifact.
FROM maven:3.8.5-openjdk-17-slim AS build

# Set the working directory in the container to /app
WORKDIR /app

# Copy the pom.xml file to the /app directory
COPY pom.xml .

# Copy the src directory to the /app directory
COPY src /app/src

# Build the application
RUN mvn clean install

# Use the official openjdk image for a lean production stage of our multi-stage build
FROM openjdk:17-slim

# Copy the built jar file to the /app directory
COPY --from=build /app/target/*.jar /app/app.jar

# Make port available to the world outside this container
EXPOSE 8081

# Run the application
CMD ["java", "-jar", "/app/app.jar"]
