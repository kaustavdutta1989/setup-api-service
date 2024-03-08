FROM openjdk:17
EXPOSE 8704
#WORKDIR /app
COPY . .
ADD target/setup-api-service.jar  app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]