package com.hcl.flight.flightsearch.controller;

import com.hcl.flight.flightsearch.model.Operator;
import com.hcl.flight.flightsearch.repository.OperatorRepository;
import com.hcl.flight.flightsearch.service.MockOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/operators")
public class MockOperatorController {

    private final MockOperatorService mockOperatorService;
    private final OperatorRepository operatorRepository;

    @Autowired
    public MockOperatorController(MockOperatorService mockOperatorService, OperatorRepository operatorRepository) {
        this.mockOperatorService = mockOperatorService;
        this.operatorRepository = operatorRepository;
    }

    @PostMapping
    public Mono<ResponseEntity<Operator>> addOperator(@RequestBody Operator operator) {
        return mockOperatorService.addOperator(operator)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<Operator> updateOperator(@PathVariable String id, @RequestBody Operator operator) {
        return operatorRepository.findById(id)
                .flatMap(existingOperator -> {
                    return operatorRepository.save(existingOperator);
                });
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteOperator(@PathVariable String id) {
        return operatorRepository.deleteById(id);
    }



}
