package com.project.paypal.service;


import com.hcl.kafka.dto.PaymentDTO;
import com.hcl.kafka.dto.PaymentValidation;
import com.project.paypal.model.OrderPayment;
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
    public final PaypalService paypalService;
    public final Producer producer;

    @KafkaListener(topics = "payment", groupId = "paymentGroup", containerFactory = "containerFactory")
    public void consumeValidate(ConsumerRecord<Long, PaymentDTO> consumerRecord) {
        Long key = consumerRecord.key();
        PaymentDTO value = consumerRecord.value();
        log.info("Avro message received for key : " + key + " value : " + value.toString());
        boolean validated = paymentService.validatePayment(value);

        if(!validated){
            PaymentValidation paymentValidation = new PaymentValidation();
            paymentValidation.setBookingId(value.getBookingId());
            paymentValidation.setStatusValidation(false);
            kafkaTemplate.send("payment_feedback", key, paymentValidation);
        }
        else{
            OrderPayment orderPayment = paypalService.createPaymentAuth(value, key).block();
            producer.sendPaymentDetails(value, orderPayment.getRedirectUrl());
        }
    }
}
