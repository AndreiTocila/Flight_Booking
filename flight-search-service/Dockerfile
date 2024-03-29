FROM maven:3.8.4-openjdk-17-slim AS build
COPY . /home/app
RUN mvn -f /home/app/flight-search-service/pom.xml clean package -DskipTests


FROM openjdk:17-slim
COPY --from=build /home/app/flight-search-service/target/flight-search-service-*.jar /usr/local/lib/flight-search-service.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/flight-search-service.jar"]