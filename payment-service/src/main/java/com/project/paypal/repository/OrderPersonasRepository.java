package com.project.paypal.repository;

import com.project.paypal.model.OrderPersonas;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPersonasRepository extends ReactiveMongoRepository<OrderPersonas, String> {
}
