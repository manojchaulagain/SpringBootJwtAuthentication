FROM openjdk:10
WORKDIR /
RUN ["curl", "-o", "kafka.tgz", "https://www-eu.apache.org/dist/kafka/2.2.0/kafka_2.11-2.2.0.tgz"]
RUN ["tar", "zxvf", "kafka.tgz"]
WORKDIR /kafka_2.11-2.2.0
CMD ["/bin/sh", "-c", "./bin/zookeeper-server-start.sh ./config/zookeeper.properties"]
EXPOSE 2181
