package com.project.paypal.controller;


import com.hcl.kafka.dto.PaymentDTO;
import com.project.paypal.service.PaypalService;
import com.project.paypal.service.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RestController
public class PaypalController {

    private final PaypalService paypalService;

    @Autowired
    private final Producer producer;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/payment/cancel")
    public String paymentCancel(){
        return "paymentCancel";
    }

    @GetMapping("/payment/error")
    public String paymentError(){
        return "paymentError";
    }

    @PostMapping("/validate")
    public void validateMessage(@RequestBody PaymentDTO paymentDTO){
        producer.sendValidate(paymentDTO);
    }
}
