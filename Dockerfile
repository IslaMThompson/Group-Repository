FROM openjdk:latest
COPY ./target/groupProject-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "groupProject-0.1.0.2-jar-with-dependencies.jar"]