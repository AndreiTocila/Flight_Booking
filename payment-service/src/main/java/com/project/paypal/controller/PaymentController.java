package com.project.paypal.controller;

import com.project.paypal.model.*;
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

    @PostMapping(value="/refund")
    public Mono<OrderRefund> completeRefund(@RequestParam("token") String token, @RequestParam(name = "iban") String iban){
        return payPalService.completeRefund(token, iban);
    }

    @PostMapping(value = "/completeAuth")
    public Mono<OrderAuthorize> completeAuthPayment(@RequestParam("token") String token,
                                                    @RequestParam(name = "iban") String iban) {
        return payPalService.completeAuthPayment(token, iban);
    }

    @PostMapping(value = "/captureAuth")
    public Mono<OrderAuthorize> captureAuthPayment(@RequestParam("token") String token,
                                                   @RequestParam(name = "iban") String iban){
        return payPalService.captureAuthOrder(token, iban);
    }

    @GetMapping(value = "/get")
    public Mono<OrderPersonas> getPayment(@RequestParam("token") String token,
                                          @RequestParam(name = "iban") String iban) {
        return payPalService.getOrder(token, iban);
    }
}