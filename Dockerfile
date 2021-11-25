FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} charity-app.jar
ENTRYPOINT ["java","-jar","/charity-app.jar"]