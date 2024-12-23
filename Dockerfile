# Stage 1: build
# Start with a Maven image that includes JDK 21
FROM maven:3.9.9-ibm-semeru-21-jammy AS build

# Copy source code and pom.xml file to /app folder
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build source code with maven
RUN mvn package -DskipTests

#Stage 2: create image
# Start with Amazon Correto JDK 21
FROM ibm-semeru-runtimes:open-21-jdk-jammy

# Set working folder to App and copy complied file from above step
WORKDIR /app
COPY --from=build /app/target/Server_Mobile-0.0.1-SNAPSHOT.jar app.jar

# Command to run the application
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "app.jar"]