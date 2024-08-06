FROM amazoncorretto:21-alpine-jdk

# Create the directory where the code will go
RUN mkdir -p /home/app
# Set the working directory
WORKDIR /home/app

# Copy everything else
COPY . .

# Build the app
RUN ./gradlew clean build -x test

# Expose the port
EXPOSE 8080

# Run the app
ENTRYPOINT [ "java", "-jar", "/home/app/build/libs/account-service-0.0.1-SNAPSHOT.jar" ]
