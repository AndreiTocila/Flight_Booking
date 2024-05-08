package com.project.paypal.service;

import com.hcl.kafka.dto.PaymentDTO;
import com.hcl.kafka.dto.PaymentValidation;
import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import com.paypal.orders.Item;
import com.paypal.orders.Money;
import com.paypal.orders.Order;
import com.paypal.payments.*;
import com.paypal.payments.Capture;
import com.paypal.payments.Refund;
import com.project.paypal.exception.OrderNotPayedException;
import com.project.paypal.model.*;
import com.project.paypal.repository.OrderAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PaypalService
{

    private final String returnUrl;

    private final String cancelUrl;

    private final OrderService orderService;

    private final OrderAuthRepository orderAuthRepository;

    private final OperatorService operatorService;

    private final AuthService authService;

    private final RefundService refundService;

    private final InvoiceService invoiceService;

    private final PaymentService paymentService;

    private final KafkaTemplate<Long, PaymentValidation> kafkaTemplate;

    private final long AVAILABLE_TIME = 10000 * 6; // 1 minutes


    @Autowired
    public PaypalService(@Value("${return.url}") String returnUrl,
                         @Value("${cancel.url}") String cancelUrl,
                         OrderService orderService,
                         OrderAuthRepository orderAuthRepository,
                         OperatorService operatorService, AuthService authService, RefundService refundService, InvoiceService invoiceService, PaymentService paymentService, KafkaTemplate<Long, PaymentValidation> kafkaTemplate) {
        this.returnUrl = returnUrl;
        this.cancelUrl = cancelUrl;
        this.orderService = orderService;
        this.orderAuthRepository = orderAuthRepository;
        this.operatorService = operatorService;
        this.authService = authService;
        this.refundService = refundService;
        this.invoiceService = invoiceService;
        this.paymentService = paymentService;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Mono<OrderPayment> createPaymentAuth(PaymentDTO paymentDTO, Long flightId) {

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("AUTHORIZE");

        AmountWithBreakdown amountWithBreakdown = new AmountWithBreakdown();
        amountWithBreakdown.currencyCode("EUR");
        amountWithBreakdown.value(paymentDTO.getPrice().toString());

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
        item.name("Flight from X to Y");
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

        Mono<Operator> mono = operatorService.findByIban(paymentDTO.getIbanOperator());

        Mono<OrderPayment> paymentMono = mono
                .flatMap(event -> {

                    PayPalEnvironment environment = new PayPalEnvironment.Sandbox(event.getClientId(), event.getClientSecret());
                    PayPalHttpClient payPalHttpClient = new PayPalHttpClient(environment);
                    try {
                        HttpResponse<Order> orderHttpResponse = payPalHttpClient.execute(ordersCreateRequest);
                        Order order = orderHttpResponse.result();

                        String redirectUrl = order.links().stream()
                                .filter(link -> link.rel().equals("approve"))
                                .findFirst()
                                .orElseThrow(NoSuchElementException::new)
                                .href();
                        System.out.println(order.status());

                        OrderAuthorize orderAuthorize = new OrderAuthorize();
                        orderAuthorize.setOrderId(order.id());
                        orderAuthorize.setStatus("INITIATED");
                        orderAuthorize.setAuthId("0");
                        orderAuthorize.setCaptureId("0");
                        orderAuthorize.setBookingId(paymentDTO.getBookingId());
                        orderAuthorize.setFlightId(flightId);
                        orderAuthorize.setCreationTime(String.valueOf(System.currentTimeMillis()));
                        orderAuthorize.setExpirationTime(orderAuthorize.getCreationTime() + AVAILABLE_TIME);
                        authService.addAuthOrder(orderAuthorize).subscribe();

                        OrderInvoice orderInvoice = new OrderInvoice();
                        orderInvoice.setInvoiceId(order.id());
                        orderInvoice.setItem_name(item.name());
                        orderInvoice.setCurrency_code(item.unitAmount().currencyCode());
                        orderInvoice.setItems_value(item.unitAmount().value());
                        orderInvoice.setItem_quantity(item.quantity());
                        invoiceService.addInvoice(orderInvoice).subscribe();
////                        InvoiceAddress invoiceAddress = new InvoiceAddress();
////                        invoiceAddress.setLine1("bucuresti str 4");
////                        invoiceAddress.setCity("Bucuresti");
////                        invoiceAddress.setCountryCode("ROM");
////
////                        com.paypal.api.payments.Phone phone = new com.paypal.api.payments.Phone();
////                        phone.setCountryCode("ROM");
////                        phone.setNationalNumber("0723232323");
////
////                        merchantInfo.setPhone(phone);
////                        merchantInfo.setFax(phone);
////
////                        merchantInfo.setAddress(invoiceAddress);
////
////                        invoice.setMerchantInfo(merchantInfo);
////
////                        Currency currency = new Currency();
////                        currency.setCurrency(item.unitAmount().currencyCode());
////                        currency.setValue(item.unitAmount().value());
////
////                        InvoiceItem invoiceItem = new InvoiceItem();
////
////                        invoice.setId(order.id());
////                        invoiceItem.setName(item.name());
////                        invoiceItem.setUnitPrice(currency);
////                        invoiceItem.setQuantity(Float.parseFloat(item.quantity()));
////
////                        List<InvoiceItem> invoiceItemList = new ArrayList<>();
////                        invoiceItemList.add(invoiceItem);
////
////                        invoice.setItems(invoiceItemList);

                        OrderPayment paymentOrder = new OrderPayment("success", order.id(), redirectUrl);
                        return Mono.just(paymentOrder);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        OrderPayment paymentOrder = new OrderPayment();
                        paymentOrder.setStatus("Error");
                        return Mono.just(paymentOrder);
                    }
                });
        return paymentMono;
    }

    @Transactional
    public Mono<OrderRefund> completeRefund(String refundId, String iban){
        CapturesRefundRequest capturesRefundRequest = new CapturesRefundRequest(refundId);

        Mono<Operator> monoBusiness = operatorService.findByIban(iban);

        return monoBusiness.flatMap(operator -> {
            PayPalEnvironment environment = new PayPalEnvironment.Sandbox(operator.getClientId(), operator.getClientSecret());
            PayPalHttpClient client = new PayPalHttpClient(environment);

            try {
                HttpResponse<Refund> response = client.execute(capturesRefundRequest);
                Refund refund = response.result();


                if (refund.status().equals("COMPLETED")) {
                    Mono<OrderRefund> mono = refundService.findByRefundId(refundId);
                    mono.subscribe(orderRefund -> {
                        orderRefund.setStatus("SUCCESS");
                        refundService.updateOrder(orderRefund, refundId).subscribe();
                    });
                    return Mono.just(new OrderRefund(refund.id(), refundId, refund.status(), System.currentTimeMillis()));
                } else {
                    // Update refund status to 'CANCELED' in database
                    Mono<OrderRefund> mono = refundService.findByRefundId(refundId);
                    mono.subscribe(orderRefund -> {
                        orderRefund.setStatus("CANCELED");
                        refundService.updateOrder(orderRefund, refundId).subscribe();
                    });
                    return Mono.just(new OrderRefund("CANCELED", refundId));
                }
            } catch (IOException e) {
                // Handle exception
                System.err.println("Error completing refund: " + e.getMessage());
                return Mono.error(e);
            }
        });
    }

    @Transactional
    public Mono<OrderAuthorize> completeAuthPayment(String token, String iban) {

        OrdersAuthorizeRequest ordersAuthorizeRequest = new OrdersAuthorizeRequest(token);
        Mono<Operator> monoBusiness = operatorService.findByIban(iban);

        Mono<OrderAuthorize> completedOrderMono = monoBusiness.flatMap(event -> {

            PayPalEnvironment environment = new PayPalEnvironment.Sandbox(event.getClientId(), event.getClientSecret());
            PayPalHttpClient payPalHttpClient = new PayPalHttpClient(environment);
            Order order = new Order();

            try {
                HttpResponse<Order> httpResponse = payPalHttpClient.execute(ordersAuthorizeRequest);
                order = httpResponse.result();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            if (order.status() != null) { // in cazul asta va avea mereu COMPLETED daca plata a fost efectuata cu succes
                final String orderId = order.id();
                Mono<OrderAuthorize> mono = authService.findByAuthOrderId(orderId);
                System.out.println(order.status());

                String authIdd = order.purchaseUnits().get(0).payments().authorizations().get(0).id();

                mono.subscribe(e -> {
                    e.setStatus("SUCCESS");
                    authService.updateAuthOrderId(orderId, authIdd).subscribe();
                });

                return Mono.just(new OrderAuthorize(orderId, orderId, authIdd, "0", order.status(),
                        order.purchaseUnits().get(0).payments().authorizations().get(0).createTime(),
                        order.purchaseUnits().get(0).payments().authorizations().get(0).expirationTime()));
            } else {
                System.out.println(order.status());
                Mono<OrderAuthorize> mono = authService.findByAuthOrderId(order.purchaseUnits().get(0).payments().authorizations().get(0).id());

                Order finalOrder1 = order;
                mono.subscribe(e -> {
                    e.setStatus("CANCELED");
                    authService.updateAuthOrder(e, finalOrder1.purchaseUnits().get(0).payments().authorizations().get(0).id()).subscribe();
                });
                return Mono.just(new OrderAuthorize("canceled", order.purchaseUnits().get(0).payments().authorizations().get(0).id()));
            }

        });
        return completedOrderMono;
    }

    @Transactional
    public Mono<OrderAuthorize> captureAuthOrder(String token, String iban) {

        AuthorizationsCaptureRequest authorizationsGetRequest = new AuthorizationsCaptureRequest(token);

        Mono<Operator> monoBusiness = operatorService.findByIban(iban);
        Mono<OrderAuthorize> orderStatusMono = authService.findByAuthAuthId(token);
        long callTime = System.currentTimeMillis();

        Mono<OrderAuthorize> resultMono = orderStatusMono.flatMap(orderStatus -> {
            String expirationTimeString = orderStatus.getExpirationTime();

            long expirationTime;
            try {
                expirationTime = Long.parseLong(expirationTimeString);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid expiration time format: " + expirationTimeString, e);
            }

            if (callTime > expirationTime && !orderStatus.getStatus().equals("SUCCESS")) {
                orderStatus.setStatus("CANCELED");
                return authService.updateAuthOrder(orderStatus, token)
                        .map(updatedOrderStatus -> new OrderAuthorize(updatedOrderStatus.getStatus(), updatedOrderStatus.getId()));
            } else {
                return monoBusiness.flatMap(event -> {
                    PayPalEnvironment environment = new PayPalEnvironment.Sandbox(event.getClientId(), event.getClientSecret());
                    PayPalHttpClient payPalHttpClient = new PayPalHttpClient(environment);
                    try {

                        Mono<OrderAuthorize> captureMono = authService.findByAuthAuthId(token);
                        HttpResponse<Capture> httpResponse = payPalHttpClient.execute(authorizationsGetRequest);
                        System.out.println(httpResponse.toString());

                        String authorization = httpResponse.result().id();
                        System.out.println(authorization);

                        captureMono.subscribe(e->{
                            orderStatus.setStatus("SUCCESS");
                            authService.updateAuthCaptureId(orderStatus.getAuthId(), authorization).subscribe();
                            System.out.println(orderStatus);
                        });
                        return Mono.just(new OrderAuthorize(
                                orderStatus.getId(),
                                orderStatus.getOrderId(),
                                orderStatus.getAuthId(),
                                authorization,
                                orderStatus.getStatus(),
                                orderStatus.getCreationTime(),
                                orderStatus.getExpirationTime(),
                                orderStatus.getBookingId(),
                                orderStatus.getIban(),
                                orderStatus.getFlightId())
                        );
                    } catch (IOException e) {
                        System.out.println(e);
                        return Mono.error(e);
                    }
                }).flatMap(updatedOrderStatus -> {
                    PaymentValidation paymentValidation = new PaymentValidation();
                    paymentValidation.setBookingId(updatedOrderStatus.getBookingId());
                    paymentValidation.setStatusValidation(true);
                    kafkaTemplate.send("payment_feedback", updatedOrderStatus.getFlightId(), paymentValidation);
                    return Mono.just(new OrderAuthorize(
                            updatedOrderStatus.getId(),
                            updatedOrderStatus.getOrderId(),
                            updatedOrderStatus.getAuthId(),
                            updatedOrderStatus.getCaptureId(),
                            updatedOrderStatus.getStatus(),
                            updatedOrderStatus.getCreationTime(),
                            updatedOrderStatus.getExpirationTime(),
                            updatedOrderStatus.getBookingId(),
                            updatedOrderStatus.getIban(),
                            updatedOrderStatus.getFlightId())
                    );
                });
            }
        });

        return resultMono;
    }


    public Mono<OrderPersonas> getOrder(String orderId, String iban) {

        OrdersGetRequest ordersGetRequest = new OrdersGetRequest(orderId);
        Mono<Operator> monoBusiness = operatorService.findByIban(iban);

        Mono<OrderPersonas> orderMono = monoBusiness.flatMap(event -> {

            PayPalEnvironment environment = new PayPalEnvironment.Sandbox(event.getClientId(), event.getClientSecret());
            PayPalHttpClient payPalHttpClient = new PayPalHttpClient(environment);

            try {
                HttpResponse<Order> httpResponse = payPalHttpClient.execute(ordersGetRequest);
                Order order = httpResponse.result();
                OrderPersonas getOrderObj = new OrderPersonas();

                getOrderObj.setPayee(order.purchaseUnits().get(0).payee());
                getOrderObj.setPayer(order.payer());

                getOrderObj.setPayerId(getOrderObj.getPayer().payerId());
                getOrderObj.setPayerEmail(getOrderObj.getPayer().email());

                getOrderObj.setPayeeId(getOrderObj.getPayee().merchantId());
                getOrderObj.setPayeeEmail(getOrderObj.getPayee().email());

                return Mono.just(getOrderObj);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return orderMono;
    }

}