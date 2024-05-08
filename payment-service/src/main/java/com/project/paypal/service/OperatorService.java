package com.project.paypal.service;

import com.project.paypal.model.Operator;
import com.project.paypal.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OperatorService {

    private final OperatorRepository operatorRepository;

    @Autowired
    public OperatorService(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    public Mono<Operator> findByIban(String iban) {
        return operatorRepository.findBusinessPlatformByIban(iban);
    }
}