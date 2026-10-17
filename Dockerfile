# Use Gradle wrapper if present; otherwise this image includes Gradle
FROM gradle:8.5-jdk17 AS builder
WORKDIR /work

# Copy everything needed for a Gradle build. We copy gradle wrapper if present.
COPY . /work

# Run the build (skip tests by default to speed up image builds; CI will run tests)
RUN if [ -x ./gradlew ]; then ./gradlew --no-daemon clean bootJar -x test; else gradle --no-daemon clean bootJar -x test; fi

# Locate the jar produced by the Spring Boot plugin
RUN mkdir -p /app/libs && cp build/libs/*.jar /app/libs/

# Runtime image
FROM eclipse-temurin:17-jre

# Create non-root user
RUN useradd -m appuser
USER appuser

WORKDIR /home/appuser

# Copy jar from builder stage
COPY --from=builder /app/libs/*.jar ./app.jar

# Expose the application port (must match application.yml)
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/home/appuser/app.jar"]