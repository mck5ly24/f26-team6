package com.example.Fit.District.repository;

import com.example.Fit.District.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Long> {

    // Find a provider using their registered email address
    Optional<Provider> findByEmail(String email);
}
