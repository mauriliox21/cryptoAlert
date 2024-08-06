FROM maven:3.8.7-eclipse-temurin-17-alpine as mvn 
RUN mkdir /app
WORKDIR /app
COPY . .
RUN mvn package
EXPOSE 8080
CMD ["java", "-jar", "/target/*.jar"]