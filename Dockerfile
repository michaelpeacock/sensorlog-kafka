# docker build -t michaelpeacock/sensorlog-kafka:v1 .
# docker push michaelpeacock/sensorlog-kafka:v1

FROM openjdk:11
COPY ./target/sensorlog-kafka-1.0.jar /tmp
WORKDIR /tmp
CMD java -jar sensorlog-kafka-1.0.jar