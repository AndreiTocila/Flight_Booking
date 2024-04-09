package com.project.paypal.service;


import com.hcl.kafka.dto.PaymentDTO;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public boolean validatePayment(PaymentDTO paymentDTO){
        return paymentDTO.getIbanClient().matches("^[A-Z]{2}[0-9]{2}[A-Z0-9]{1,30}$") &&
                            paymentDTO.getIbanOperator().matches("^[A-Z]{2}[0-9]{2}[A-Z0-9]{1,30}$") &&
                            paymentDTO.getPrice() > 0;
    }
}
