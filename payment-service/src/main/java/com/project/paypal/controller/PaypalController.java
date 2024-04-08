package com.project.paypal.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.project.PaymentDTO;
import com.project.paypal.service.PaypalService;
import com.project.paypal.service.Producer;
import com.project.paypal.utils.PaymentMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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


    @PostMapping("/payment/create")
    public RedirectView createPayment(
            @RequestParam Double amount,
            @RequestParam String currency,
            @RequestParam String method,
            @RequestParam String intent,
            @RequestParam String description) {
        try {
            String cancelUrl = "http://localhost:8080/payment/cancel";
            String successUrl = "http://localhost:8080/payment/success";
            Payment payment = paypalService.createPayment(
                    amount,
                    currency,
                    method,
                    intent,
                    description,
                    cancelUrl,
                    successUrl
            );
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    return new RedirectView(links.getHref());
                }
            }
        } catch (PayPalRESTException e) {
            System.out.println(e);
        }
        return new RedirectView("/payment/error");
    }

    @GetMapping("/payment/success")
    public String paymentSuccess(@RequestParam("paymentId") String paymentId,
                                 @RequestParam("PayerID") String payerId){
        try{
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                return "paymentSuccess";
            }
        } catch (PayPalRESTException e){
            System.out.println(e);
        }
        return "paymentSuccess";
    }

    @GetMapping("/payment/cancel")
    public String paymentCancel(){
        return "paymentCancel";
    }

    @GetMapping("/payment/error")
    public String paymentError(){
        return "paymentError";
    }

//    @PostMapping("/publish")
//    public void sendMessage(@RequestBody PaymentMessage paymentMessage){
//        producer.sendMessage(paymentMessage);
//    }

    @PostMapping("/validate")
    public void validateMessage(@RequestBody PaymentDTO paymentDTO){
//        if (!paymentDTO.getIbanClient().matches("^[A-Z]{2}[0-9]{2}[A-Z0-9]{1,30}$")) {
//            throw new IllegalArgumentException("Invalid IBAN for client");
//        }
//        if (!paymentDTO.getIbanOperator().matches("^[A-Z]{2}[0-9]{2}[A-Z0-9]{1,30}$")) {
//            throw new IllegalArgumentException("Invalid IBAN for operator");
//        }
        producer.sendValidate(paymentDTO);
    }
}
