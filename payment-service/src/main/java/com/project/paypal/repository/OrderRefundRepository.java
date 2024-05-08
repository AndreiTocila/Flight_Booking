package com.project.paypal.repository;

import com.project.paypal.model.OrderRefund;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface OrderRefundRepository extends ReactiveMongoRepository<OrderRefund, String>
{
    Mono<OrderRefund> findByRefundId(String id);
}
