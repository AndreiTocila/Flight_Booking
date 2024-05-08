package com.project.paypal.repository;

import com.project.paypal.model.OrderInvoice;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface OrderInvoiceRepository extends ReactiveMongoRepository<OrderInvoice, String> {
    Mono<OrderInvoice> findByInvoiceId(String invoiceId);
}
