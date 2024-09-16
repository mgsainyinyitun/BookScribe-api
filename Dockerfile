FROM openjdk:17-jdk-slim
COPY target/BookScribe-0.0.1-SNAPSHOT.jar bookscribe.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","bookscribe.jar"]