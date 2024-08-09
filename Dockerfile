FROM maven:3.8.7-eclipse-temurin-17-alpine as mvn 
RUN mkdir /fonts
WORKDIR /fonts
COPY . .
WORKDIR /
RUN cp /etc/secrets/firebase-service-account.json ./fonts/src/main/resources/firebase-service-account.json
WORKDIR /fonts
RUN mvn package -DskipTests
RUN cp ./target/cryptoalert-0.0.1-SNAPSHOT.jar ../app.jar
WORKDIR /
RUN rm -r fonts
EXPOSE 8080
CMD ["java", "-jar", "/app.jar"]