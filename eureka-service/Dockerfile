FROM maven:3.8.4-openjdk-17-slim AS build
COPY . /home/app
RUN mvn -f /home/app/eureka-service/pom.xml clean package -DskipTests

FROM openjdk:17-slim
VOLUME /tmp
EXPOSE 8761

COPY --from=build /home/app/eureka-service/target/eureka-service-*.jar /usr/local/lib/eureka-service.jar

ENTRYPOINT ["java","-jar","/usr/local/lib/eureka-service.jar"]
