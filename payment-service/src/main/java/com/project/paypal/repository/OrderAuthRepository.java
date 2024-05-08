package com.project.paypal.repository;

import com.project.paypal.model.OrderAuthorize;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface OrderAuthRepository extends ReactiveMongoRepository<OrderAuthorize, String> {
    Mono<OrderAuthorize> findByOrderId(String orderId);
    Mono<OrderAuthorize> findByAuthId(String authId);
    Mono<OrderAuthorize> findByCaptureId(String captureId);
}
