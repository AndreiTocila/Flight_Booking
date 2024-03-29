package com.hcl.flight.userservice.repository;

import com.hcl.flight.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @NonNull
    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

}
