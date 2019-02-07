FROM openjdk:10
COPY ./target/SpringBootJwtAuthentication-0.0.1.jar /
WORKDIR /
ENTRYPOINT java -jar --add-modules java.xml.bind SpringBootJwtAuthentication-1.0.0.jar