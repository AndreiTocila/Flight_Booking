package com.project.paypal.repository;

import com.paypal.api.payments.Payment;
import com.project.paypal.model.OrderStatus;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PaypalRepository extends ReactiveMongoRepository<Payment, String> {
//    Mono<OrderStatus> findByOrderId(String orderId);
}
