package com.project.paypal.repository;

import com.paypal.api.payments.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaypalRepository extends MongoRepository<Payment, String> {

}
