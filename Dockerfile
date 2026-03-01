# ---- build stage ----
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline

COPY src ./src
RUN mvn -q -DskipTests package

# ---- run stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENV SERVER_PORT=8080

# Seguridad mínima: correr como user no-root
RUN useradd -r -u 10001 appuser && chown -R appuser:appuser /app
USER 10001

ENTRYPOINT ["java","-jar","/app/app.jar"]