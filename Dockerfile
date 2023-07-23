# Start with a base image containing Java runtime (AdoptOpenJDK)
FROM openjdk:17-jdk-slim AS build

# Set the working directory in the image to "/app"
WORKDIR /app

# Copy the Gradle executable to the image
COPY gradlew ./

# Copy the 'gradle' folder to the image
COPY gradle ./gradle

# Give permission to execute the gradle script
RUN chmod +x ./gradlew

# Copy the rest of the application source code
COPY . .

# Use Gradle to build the application
RUN sh ./gradlew build

# Set up a second stage, which will only keep the compiled application and not the build tools and source code
FROM openjdk:17-jdk-slim

# Set the working directory to '/app'
WORKDIR /app

# Copy the jar file from the first stage
COPY --from=build /app/build/libs/*.jar app.jar

# Set the startup command to execute the jar
CMD ["java", "-jar", "/app/app.jar"]