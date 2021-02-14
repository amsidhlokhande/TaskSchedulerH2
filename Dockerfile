FROM openjdk:8-jdk-alpine
VOLUME ["/tmp"]
COPY target/taskschedulerh2-*.jar taskschedulerh2.jar
COPY src/ src
COPY pom.xml pom.xml
COPY Dockerfile Dockerfile
ENTRYPOINT ["java","-jar","taskschedulerh2.jar"]