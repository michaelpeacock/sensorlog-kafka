package io.confluent.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.web.bind.annotation.RestController
public class SensorLogRestController {
  @Autowired
  SensorLogKafkaProducer producer;

  private ObjectMapper mapper = new ObjectMapper();

  @PostMapping({"/api/sensorlog/update"})
  public void processSensorLogUpdates(@RequestBody String updates) {
    System.out.println("sensorlog updates: " + updates);

    try {
      producer.send("sensorlog", mapper.readTree(updates));
    } catch
    (JsonProcessingException e) {
      e.printStackTrace();
    }
  }
}
