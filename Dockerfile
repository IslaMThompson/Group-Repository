FROM amazoncorretto:17
COPY ./target/groupProject-0.1.0.4-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "groupProject-0.1.0.4-jar-with-dependencies.jar"]