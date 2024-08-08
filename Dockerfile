FROM maven:3.8.7-eclipse-temurin-17-alpine as mvn 
RUN mkdir /fonts
WORKDIR /fonts
COPY . .
RUN mvn package -DskipTests
EXPOSE 8080
CMD ["java", "-jar", "/fonts/target/cryptoalert-0.0.1-SNAPSHOT.jar"]