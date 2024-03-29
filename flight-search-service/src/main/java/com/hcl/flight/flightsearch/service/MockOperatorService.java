package com.hcl.flight.flightsearch.service;

import com.hcl.flight.flightsearch.model.Operator;
import com.hcl.flight.flightsearch.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MockOperatorService {

    private final OperatorRepository operatorRepository;

    @Autowired
    public MockOperatorService(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    public Mono<Operator> addOperator(Operator operator) {
        if (operator.getName() == null || operator.getIban() == null) {
            return Mono.error(new RuntimeException("Operator name and IBAN must be provided"));
        }
        return operatorRepository.save(operator);
    }

}
