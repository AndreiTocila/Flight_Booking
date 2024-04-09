package com.project.paypal.service;

import com.project.PaymentDTO;
import com.project.PaymentValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class Consumer {

    public final PaymentService paymentService;
    public final KafkaTemplate<Long, PaymentValidation> kafkaTemplate;

    @KafkaListener(topics = "payment", groupId = "paymentGroup")
    public void consumeValidate(ConsumerRecord<Long, PaymentDTO> consumerRecord) {
        Long key = consumerRecord.key();
        PaymentDTO value = consumerRecord.value();
        log.info("Avro message received for key : " + key + " value : " + value.toString());
        boolean validated = paymentService.validatePayment(value);
        PaymentValidation paymentValidation = new PaymentValidation();
        paymentValidation.setBookingId(value.getBookingId());
        paymentValidation.setStatusValidation(validated);
        kafkaTemplate.send("payment-feedback", key, paymentValidation);
    }
}
