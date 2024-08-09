FROM maven:3.8.7-eclipse-temurin-17-alpine as mvn 
RUN mkdir /fonts
WORKDIR /fonts
COPY . .
# WORKDIR /
# WORKDIR /fonts
RUN mvn package -DskipTests
RUN cp ./target/cryptoalert-0.0.1-SNAPSHOT.jar ../app.jar
WORKDIR /
# RUN rm -r fonts
EXPOSE 8080
# CMD ["cp", "./etc/secrets/firebase-service-account.json", "./fonts/target/classes/firebase-service-account.json"];[ "java", "-jar", "/fonts/target/cryptoalert-0.0.1-SNAPSHOT.jar"]
CMD cp ./etc/secrets/firebase-service-account.json ./fonts/rsc/main/resources/firebase-service-account.json ; cp ./etc/secrets/firebase-service-account.json ./fonts/target/classes/firebase-service-account.json ; java -jar /fonts/target/cryptoalert-0.0.1-SNAPSHOT.jar