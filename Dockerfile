FROM openjdk:10

WORKDIR .

COPY target/SpringBootJwtAuthentication-0.0.1.jar .

ENTRYPOINT java -jar --add-modules java.xml.bind SpringBootJwtAuthentication-1.0.0.jar