package com.project.paypal.service;

import com.project.paypal.model.OrderAuthorize;
import com.project.paypal.model.OrderPayment;
import com.project.paypal.model.OrderStatus;
import com.project.paypal.repository.OrderAuthRepository;
import com.project.paypal.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderAuthRepository orderAuthRepository){
        this.orderRepository = orderRepository;
    }

    public Mono<OrderStatus> addOrder(OrderStatus orderStatus){
        return orderRepository.save(orderStatus);
    }


    public Mono<OrderStatus> findByOrderId(String orderId) {
        return orderRepository.findByOrderId(orderId);
    }


//    public Mono<OrderPayment> findByPayId(String orderId) {
//        return  orderRepository.findByPayId(orderId);
//    }

    public Mono<OrderStatus> updateOrder(OrderStatus orderStatus, String orderId) {
        Mono<OrderStatus> existingOrderStatus = orderRepository.findByOrderId(orderId);
        return existingOrderStatus.flatMap(event -> {
            event.setStatus(orderStatus.getStatus());
            return orderRepository.save(event);
        });
    }
}
