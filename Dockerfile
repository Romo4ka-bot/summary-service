FROM openjdk:8-jre-alpine

WORKDIR /app

COPY /target/summary-service-0.1.jar /app/

ENTRYPOINT ["java", "-jar", "summary-service-0.1.jar"]