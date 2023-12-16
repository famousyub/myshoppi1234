FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
COPY lombok.config /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM openjdk:11
COPY --from=build /home/app/target/ecommerce-0.0.1-SNAPSHOT.jar /usr/share/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/share/app.jar"]
