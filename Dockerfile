FROM openjdk:8-jdk-alpine
MAINTAINER Borys Shvyryd bormanpgg@gmail.com
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} charity-app.jar
ENTRYPOINT ["java","-jar","/charity-app.jar"]

#FROM maven:3.8.1-jdk-8
#WORKDIR /charity
#COPY . .
#RUN mvn clean install
#CMD mvn CharityApplication:run