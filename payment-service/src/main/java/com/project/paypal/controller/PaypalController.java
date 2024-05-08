package com.project.paypal.controller;


import com.hcl.kafka.dto.PaymentDTO;
import com.project.paypal.model.OrderAuthorize;
import com.project.paypal.service.AuthService;
import com.project.paypal.service.PaypalService;
import com.project.paypal.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

@Controller
//@RequiredArgsConstructor
@RestController
public class PaypalController {

    private final PaypalService paypalService;
    private final AuthService authService;
    private final Producer producer;

    @Autowired
    public PaypalController(PaypalService paypalService, AuthService authService, Producer producer){
        this.paypalService = paypalService;
        this.authService = authService;
        this.producer = producer;
    }

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

    @GetMapping("/payment/success")
    public String successPayment() {
        return "Payment success";
    }

    @PostMapping("/validate")
    public void validateMessage(@RequestBody PaymentDTO paymentDTO){
        producer.sendValidate(paymentDTO);
    }
}
