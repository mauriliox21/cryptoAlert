FROM maven:3.8.7-eclipse-temurin-17-alpine as mvn 
COPY . .
RUN mvn package
RUN mkdir /app
WORKDIR /app
COPY target/*.jar  /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/app.jar"]