FROM maven:3.8.4-openjdk-17-slim AS build
COPY . /home/app
RUN mvn -f /home/app/operator-service/pom.xml clean package -DskipTests

FROM openjdk:17-slim
COPY --from=build /home/app/operator-service/target/operator-service-*.jar /usr/local/lib/operator-service.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/operator-service.jar"]
