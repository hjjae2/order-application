FROM openjdk:11
COPY build/libs/assignment-1.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]