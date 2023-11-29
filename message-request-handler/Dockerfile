FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

COPY mvnw mvnw
# clean up the file
RUN sed -i 's/\r$//' mvnw
COPY .mvn .mvn

COPY pom.xml pom.xml
COPY src src
RUN ["./mvnw", "clean", "install"]

FROM eclipse-temurin:17-jdk-alpine AS final
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]