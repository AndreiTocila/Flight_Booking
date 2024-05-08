package com.project.paypal.service;

import com.project.paypal.model.OrderAuthorize;
import com.project.paypal.repository.OrderAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthService {

    private final OrderAuthRepository orderAuthRepository;

    @Autowired
    public AuthService(OrderAuthRepository orderAuthRepository) {
        this.orderAuthRepository = orderAuthRepository;
    }

    public Mono<OrderAuthorize> addAuthOrder(OrderAuthorize orderAuthorize) {
        return orderAuthRepository.save(orderAuthorize);
    }

    public Mono<OrderAuthorize> findByAuthOrderId(String orderId) {
        return orderAuthRepository.findByOrderId(orderId);
    }

    public Mono<OrderAuthorize> findByAuthAuthId(String authId) {
        return orderAuthRepository.findByAuthId(authId);
    }

    public Mono<OrderAuthorize> findByAuthCaptureId(String captureId) {
        return orderAuthRepository.findByCaptureId(captureId);
    }

    public Mono<OrderAuthorize> updateAuthOrder(OrderAuthorize orderStatus, String orderId) {
        Mono<OrderAuthorize> existingAuthOrderStatus = orderAuthRepository.findByOrderId(orderId);
        return existingAuthOrderStatus.flatMap(event -> {
            event.setStatus(orderStatus.getStatus());
            return orderAuthRepository.save(event);
        });
    }

    public Mono<OrderAuthorize> updateAuthOrderId(String orderId, String authId) {
        Mono<OrderAuthorize> existingOrderId = orderAuthRepository.findByOrderId(orderId);
        return existingOrderId.flatMap(event -> {
            event.setAuthId(authId);
            return orderAuthRepository.save(event);
        });
    }

    public Mono<OrderAuthorize> updateAuthCaptureId(String orderId, String captureId) {
        Mono<OrderAuthorize> existingOrderId = orderAuthRepository.findByAuthId(orderId);
        return existingOrderId.flatMap(event -> {
            event.setCaptureId(captureId);
            return orderAuthRepository.save(event);
        });
    }
}
