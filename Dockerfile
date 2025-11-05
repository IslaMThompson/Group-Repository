FROM amazoncorretto:17
COPY ./target/grpProject.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "grpProject.jar", "db:3306", "30000"]