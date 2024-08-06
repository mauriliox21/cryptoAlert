FROM maven:3-openjdk-17-slim as mvn 
EXPOSE 8080
COPY . .
RUN mvn package
RUN mkdir /app
WORKDIR /app
COPY target/*.jar  /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]