FROM openjdk:10
WORKDIR /
ADD ./target/SpringBootJwtAuthentication-0.0.1.jar App.jar
ENTRYPOINT java -jar App.jar
EXPOSE 8080