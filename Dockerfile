FROM openjdk:8
ADD target/docker-0.0.1-SNAPSHOT.jar app.jar
ARG ENVIRONMENT
ENV SPRING_PROFILES_ACTIVE=${ENVIRONMENT}
ENTRYPOINT ["java", "-jar", "app.jar"]