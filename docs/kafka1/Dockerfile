FROM openjdk:10
ARG listeners
WORKDIR /
RUN ["curl", "-o", "kafka.tgz", "https://www-eu.apache.org/dist/kafka/2.2.0/kafka_2.11-2.2.0.tgz"]
RUN ["tar", "zxvf", "kafka.tgz"]
#ADD docs/kafka/kafka /kafka
WORKDIR /kafka_2.11-2.2.0
RUN sleep 15 && sed -i 's/zookeeper.connect=localhost:2181/zookeeper.connect=zookeeper:2181/g' ./config/server.properties
RUN sed -i 's/broker.id=0/broker.id=11/g' ./config/server.properties
RUN sed -i 's/kafka-logs/kafka-logs1/g' ./config/server.properties
RUN sed -i "$ a listeners=${listeners}" ./config/server.properties
RUN sed -i "$ a advertised.listeners=${listeners}" ./config/server.properties
ENTRYPOINT ["/bin/sh", "-c", "./bin/kafka-server-start.sh ./config/server.properties"]
EXPOSE 9094