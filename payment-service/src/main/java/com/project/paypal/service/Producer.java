package com.project.paypal.service;


import com.hcl.kafka.dto.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class Producer {

    @Autowired
    private KafkaTemplate<Long, PaymentDTO> kafkaValidate;
    @Autowired
    private KafkaTemplate<String, String> kafkaNotification;

    public void sendValidate(PaymentDTO paymentDTO) {
        CompletableFuture<SendResult<Long, PaymentDTO>> future = kafkaValidate.send("payment", 23L, paymentDTO);
        future.whenComplete((result, exception) ->{
            if(exception == null){
                System.out.println("Sent message = [" + paymentDTO +
                                   "] with offset = [" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message = [" +
                                   paymentDTO + "] due to : " + exception.getMessage());
            }
        });
    }

    public void sendPaymentDetails(PaymentDTO paymentDTO, String redirectUrl) {
        CompletableFuture<SendResult<String, String>> future = kafkaNotification.send("notification_payment", paymentDTO.getBookingId(), redirectUrl);
        future.whenComplete((result, exception) ->{
            if(exception == null){
                System.out.println("Sent message = [" + paymentDTO +
                                   "] with offset = [" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message = [" +
                                   paymentDTO + "] due to : " + exception.getMessage());
            }
        });
    }
}
