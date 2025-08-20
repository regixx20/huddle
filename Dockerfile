# syntax=docker/dockerfile:1.7
FROM maven:3.9.7-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn -q -DskipTests dependency:go-offline
COPY src ./src
# Repackage pour obtenir un artefact exécutable (jar ou war bootable)
RUN --mount=type=cache,target=/root/.m2 mvn -q -DskipTests package spring-boot:repackage

FROM eclipse-temurin:21-jre
WORKDIR /app

# Copie l’artefact (jar ou war bootable) sans renommer
COPY --from=build /app/target/*.[jw]ar /app/

EXPOSE 8080

# Lance le premier jar/war trouvé, SANS re-préfixer par /app/
ENTRYPOINT ["sh","-c","exec java -jar $(ls /app/*.[jw]ar | head -n 1)"]
