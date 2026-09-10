FROM maven:3.9-eclipse-temurin-17 AS dependencies
WORKDIR /BUILD 
COPY pom.xml .
RUN mvn dependency:go-offline

FROM dependencies AS builder 
COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:17-jre AS runtime
WORKDIR /APP
COPY --from=builder /BUILD/target/*jar app.jar
ENTRYPOINT [ "java","-jar","app.jar" ]