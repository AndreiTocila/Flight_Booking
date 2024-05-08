package com.project.paypal.repository;

import com.project.paypal.model.Operator;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface OperatorRepository extends ReactiveMongoRepository<Operator, String> {

    Mono<Operator> findBusinessPlatformByIban(String iban);
}
