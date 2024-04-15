package com.hcl.flight.userservice.repository;

import com.hcl.flight.userservice.model.Operator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long> {

}
