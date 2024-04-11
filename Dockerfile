FROM openjdk:11-jdk-slim

LABEL maintainer="guillenmaldonadoeduardo@gmail.com"

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "--add-opens", "java.base/java.lang=ALL-UNNAMED","app.jar","--server.port=10410"]