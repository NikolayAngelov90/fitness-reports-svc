FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY app/fitness-reports-svc-*.jar app.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]