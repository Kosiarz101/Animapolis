FROM gradle:jdk25-alpine AS builder
WORKDIR /build

COPY configserver/build.gradle settings.gradle ./
RUN gradle dependencies --no-daemon || true

COPY configserver ./configserver
COPY employee ./employee
COPY healthcare ./healthcare
RUN gradle :configserver:bootJar --no-daemon -x test

FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

RUN addgroup -S config_user && adduser -S config_user -G config_user
USER config_user:config_user

COPY --from=builder /build/configserver/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]