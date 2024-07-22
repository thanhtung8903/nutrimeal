FROM eclipse-temurin:21.0.1_12-jdk-alpine AS build
 
WORKDIR /build

COPY .mvn/ ./.mvn
COPY mvnw pom.xml ./
RUN sed -i 's/\r$//' mvnw
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

COPY . .
RUN sed -i 's/\r$//' mvnw
RUN chmod +x mvnw  
RUN ./mvnw package -DskipTests

FROM eclipse-temurin:21.0.1_12-jdk-alpine
WORKDIR /app
COPY --from=build /build/target/*.jar run.jar
ENTRYPOINT ["java", "-jar", "/app/run.jar"]
