package com.hcl.bookingservice.kafka;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.dao.DataAccessException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class PaymentKafkaConsumer
{

    @KafkaListener(topics = "payment_feedback", groupId = "booking_group", containerFactory = "containerFactory")
    public void consume(ConsumerRecord<String, String> record,
                        Acknowledgment ak)
    {
        System.out.println(record.value());
        throw new NullPointerException();
//        System.out.println(record.value());
//        ak.acknowledge();
    }
}
