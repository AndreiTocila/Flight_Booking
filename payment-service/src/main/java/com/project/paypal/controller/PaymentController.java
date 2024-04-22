package com.project.paypal.controller;

import com.project.paypal.model.OrderComplete;
import com.project.paypal.model.OrderPayment;
import com.project.paypal.model.OrderPersonas;
import com.project.paypal.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/paypal")
public class PaymentController {

    private final PaypalService payPalService;

    @Autowired
    public PaymentController(PaypalService payPalService) {
        this.payPalService = payPalService;
    }

    @PostMapping("/init")
    public OrderPayment createPayment(@RequestParam(name = "sum") Double sum) {
        return payPalService.createPayment(sum);
    }

    @PostMapping(value = "/capture")
    public OrderComplete completePayment(@RequestParam("token") String token) {
        return payPalService.completePayment(token);
    }

    @GetMapping(value = "/get")
    public Mono<OrderPersonas> getPayment(@RequestParam("token") String token) {
        return payPalService.getOrder(token);
    }
}