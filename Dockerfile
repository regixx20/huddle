# syntax=docker/dockerfile:1.7
FROM maven:3.9.7-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn -q -DskipTests dependency:go-offline
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn -q -DskipTests package

FROM tomcat:10.1-jdk17-temurin

# Nettoie les apps par défaut
RUN rm -rf /usr/local/tomcat/webapps/*

# Copie ton WAR buildé en ROOT.war
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
