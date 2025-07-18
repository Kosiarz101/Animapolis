FROM eclipse-temurin:21-jdk-alpine-3.21

WORKDIR /app

COPY build/libs/employee-0.0.1.jar employee.jar

EXPOSE 8011

ENTRYPOINT ["java", "-jar", "employee.jar"]