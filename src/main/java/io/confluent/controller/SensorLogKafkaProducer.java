package io.confluent.controller;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.Properties;
import javax.annotation.PostConstruct;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.connect.json.JsonSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class SensorLogKafkaProducer {
    private static final Properties props = new Properties();

    @Value("${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    private KafkaProducer<String, JsonNode> producer;

    @PostConstruct
    private void initialize() {
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        producer = new KafkaProducer<>(props);
    }

    public void send(String topic, JsonNode data) {
        producer.send(new ProducerRecord<String, JsonNode>(topic, data));
    }
}
