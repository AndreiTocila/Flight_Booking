#FROM maven:3.8.4-openjdk-17-slim AS build
#COPY . /home/app
#RUN mvn -f /home/app/notification-service/pom.xml clean package -DskipTests
#
#FROM openjdk:17-slim
#COPY --from=build /home/app/notification-service/target/notification-service-*.jar /usr/local/lib/notification-service.jar
#
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/usr/local/lib/notification-service.jar"]
FROM eclipse-temurin:17
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]