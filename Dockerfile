FROM maven:3.8.4-openjdk-17-slim AS build

COPY . /app
WORKDIR /app

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
COPY --from=build /app/target/superpatos-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]