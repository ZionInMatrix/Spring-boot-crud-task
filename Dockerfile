FROM openjdk:12
ADD target/spring-boot-crud-task-0.0.1-SNAPSHOT.jar spring-boot-crud-task.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-boot-crud-task.jar"]