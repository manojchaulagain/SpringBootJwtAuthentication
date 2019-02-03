FROM openjdk:10-jre-slim
COPY ./target/SpringBootJwtAuthentication-0.0.1.jar /usr/src/anoush/
WORKDIR /usr/src/anoush
EXPOSE 8080
CMD ["java", "-jar", "SpringBootJwtAuthentication-1.0.0.jar"]