# Build stage
FROM gradle:8.7-jdk17 AS builder

WORKDIR /app
COPY build.gradle settings.gradle ./
COPY src/ src/
RUN gradle build --no-daemon -x test

# Runtime stage
FROM amazoncorretto:17-alpine-jdk

WORKDIR /app
COPY  --from=builder /app/build/libs/spring-teacher6-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]