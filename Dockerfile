# Etapa 1: compila o projeto com Maven (não precisa do mvnw)
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests clean package

# Etapa 2: roda a API
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/pokedex-0.0.1-SNAPSHOT.jar app.jar
CMD ["sh", "-c", "java -jar app.jar"]
