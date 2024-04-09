#FROM maven:3.8.4-openjdk-17-slim AS build
#RUN mkdir /project
#COPY . /project
#WORKDIR /project
#RUN mvn clean compile package -DskipTests
#
#FROM openjdk:17.0.2-slim
#RUN apt-get update -y && apt-get install -y dumb-init
#RUN mkdir /app
#RUN useradd javauser && groupadd javausergroup && usermod -a -G javausergroup javauser
#COPY target/*.jar app/booking-service.jar
#WORKDIR /app
#RUN chown -R javauser:javausergroup /app
#USER javauser
#CMD "dumb-init" "java" "-jar" "booking-service.jar"

FROM eclipse-temurin:17
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]