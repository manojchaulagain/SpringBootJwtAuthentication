FROM openjdk:10
WORKDIR /
RUN ["curl", "-o", "kafka.tgz", "https://www-eu.apache.org/dist/kafka/2.2.0/kafka_2.11-2.2.0.tgz"]
RUN ["tar", "zxvf", "kafka.tgz"]
WORKDIR /kafka_2.11-2.2.0
RUN sed -i 's/localhost/zookeeper/g' ./config/server.properties
CMD ["/bin/sh", "-c", "./bin/kafka-server-start.sh ./config/server.properties"]
EXPOSE 9092