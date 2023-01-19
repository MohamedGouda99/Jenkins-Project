FROM openjdk:8-jre-alpine

EXPOSE 8080

COPY ./target/ /app/
WORKDIR /app

CMD java -jar java-maven-app-*.jar
