package com.project.paypal.service;

import com.paypal.orders.Order;
import com.project.paypal.model.*;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import com.project.paypal.repository.OrderPersonasRepository;
import com.project.paypal.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PayPalService {

    private final PayPalHttpClient payPalHttpClient;

    private final String returnUrl;

    private final String cancelUrl;

    private final OrderService orderService;

    private final OrderPersonasRepository orderPersonasRepository;


    @Autowired
    public PayPalService(PayPalHttpClient payPalHttpClient,
                         @Value("${return.url}") String returnUrl,
                         @Value("${cancel.url}") String cancelUrl,
                         OrderService orderService, OrderPersonasRepository orderPersonasRepository) {
        this.payPalHttpClient = payPalHttpClient;
        this.returnUrl = returnUrl;
        this.cancelUrl = cancelUrl;
        this.orderService = orderService;
        this.orderPersonasRepository = orderPersonasRepository;
    }

    public OrderPayment createPayment(Double payAmount) {

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        AmountWithBreakdown amountWithBreakdown = new AmountWithBreakdown();
        amountWithBreakdown.currencyCode("EUR");
        amountWithBreakdown.value(payAmount.toString());

        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest();
        purchaseUnitRequest.amountWithBreakdown(amountWithBreakdown);

        Money money = new Money();
        money.currencyCode(amountWithBreakdown.currencyCode());
        money.value(amountWithBreakdown.value());

        AmountBreakdown amountBreakdown = new AmountBreakdown();
        amountBreakdown.itemTotal(money);
        amountWithBreakdown.amountBreakdown(amountBreakdown);

        Item item = new Item();
        item.category("DIGITAL_GOODS");
        item.quantity("1");
        item.name("Flight");
        item.description("Flight");
        item.unitAmount(money);

        purchaseUnitRequest.items(List.of(item));
        purchaseUnitRequest.amountWithBreakdown().amountBreakdown().itemTotal(money);
        orderRequest.purchaseUnits(List.of(purchaseUnitRequest));

        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.returnUrl(returnUrl);
        applicationContext.cancelUrl(cancelUrl);
        applicationContext.userAction("PAY_NOW");

        orderRequest.applicationContext(applicationContext);
        OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest().requestBody(orderRequest);

        try {
            HttpResponse<Order> orderHttpResponse = payPalHttpClient.execute(ordersCreateRequest);
            Order order = orderHttpResponse.result();

            String redirectUrl = order.links().stream()
                    .filter(link -> link.rel().equals("approve"))
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new)
                    .href();

            OrderStatus orderStatus = new OrderStatus();
            orderStatus.setOrderId(order.id());
            orderStatus.setStatus("INITIATED");
            orderService.addOrder(orderStatus).subscribe();

            return new OrderPayment("success", order.id(), redirectUrl);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            OrderPayment paymentOrder = new OrderPayment();
            paymentOrder.setStatus("Error");
            return paymentOrder;
        }
    }

    public OrderComplete completePayment(String token) {
        OrdersCaptureRequest ordersCaptureRequest = new OrdersCaptureRequest(token);
        Order order = new Order();
        try {
            HttpResponse<Order> httpResponse = payPalHttpClient.execute(ordersCaptureRequest);
            order = httpResponse.result();
            System.out.println(order.status());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (order.status() != null) { // in cazul asta va avea mereu COMPLETED daca plata a fost efectuata cu succes
            final String orderId = order.id();
            Mono<OrderStatus> mono = orderService.findByOrderId(orderId);

            mono.subscribe(e -> {
                e.setStatus("SUCCESS");
                orderService.updateOrder(e, orderId).subscribe();
            });
            return new OrderComplete(order.status(), token);
        } else {
            Mono<OrderStatus> mono = orderService.findByOrderId(token);
            mono.subscribe(e -> {
                e.setStatus("CANCELED");
                orderService.updateOrder(e, e.getOrderId()).subscribe();
            });
            return new OrderComplete("canceled", token);
        }
    }

    public Mono<OrderPersonas> getOrder(String orderId) {
        OrdersGetRequest ordersGetRequest = new OrdersGetRequest(orderId);

        try {
            HttpResponse<Order> httpResponse = payPalHttpClient.execute(ordersGetRequest);
            Order order = httpResponse.result();
            OrderPersonas getOrderObj = new OrderPersonas();

            Payer payer = order.payer();
            getOrderObj.setPayerId(payer.payerId());
            getOrderObj.setPayerEmail(payer.email());

            Payee payee = order.purchaseUnits().get(0).payee();
            getOrderObj.setPayeeId(payee.merchantId());
            getOrderObj.setPayeeEmail(payee.email());

            return orderPersonasRepository.save(getOrderObj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}