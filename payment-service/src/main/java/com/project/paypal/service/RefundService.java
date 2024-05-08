package com.project.paypal.service;

import com.project.paypal.model.OrderRefund;
import com.project.paypal.repository.OrderRefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RefundService {

    private final OrderRefundRepository orderRefundRepository;

    @Autowired
    public RefundService(OrderRefundRepository orderRefundRepository) {
        this.orderRefundRepository = orderRefundRepository;
    }

    public Mono<OrderRefund> findByRefundId(String id) {
        return orderRefundRepository.findByRefundId(id);
    }

    public Mono<OrderRefund> updateOrder(OrderRefund orderRefund, String orderId) {
        Mono<OrderRefund> existingOrderRefund = orderRefundRepository.findByRefundId(orderId);
        return existingOrderRefund.flatMap(event -> {
            event.setStatus(orderRefund.getStatus());
            return orderRefundRepository.save(event);
        });
    }
}
