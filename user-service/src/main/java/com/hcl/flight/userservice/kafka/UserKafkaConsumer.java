package com.hcl.flight.userservice.kafka;

import com.hcl.flight.userservice.service.KafkaService;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class UserKafkaConsumer {
  private final KafkaService kafkaService;

  public UserKafkaConsumer(KafkaService kafkaService) {
    this.kafkaService = kafkaService;
  }

  @KafkaListener(
      topics = "seat_reservation",
      groupId = "admin_group",
      containerFactory = "containerFactory")
  public void consume(ConsumerRecord<Long, GenericRecord> record, Acknowledgment ak) {
    kafkaService.sendAdminFeedBack(record);
    System.out.println(record);
    ak.acknowledge();
  }
}
