package com.project.paypal.service;

import com.project.paypal.model.OrderInvoice;
import com.project.paypal.repository.OrderInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class InvoiceService {

    private final OrderInvoiceRepository orderInvoiceRepository;

    @Autowired
    public InvoiceService(OrderInvoiceRepository orderInvoiceRepository){
        this.orderInvoiceRepository = orderInvoiceRepository;
    }

    public Mono<OrderInvoice> findByInvoiceId(String id){
        return orderInvoiceRepository.findByInvoiceId(id);
    }

    public Mono<OrderInvoice> addInvoice(OrderInvoice orderInvoice){
        return orderInvoiceRepository.save(orderInvoice);
    }
}
