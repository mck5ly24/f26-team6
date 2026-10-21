
package com.example.Fit.District.repository;

import com.example.Fit.District.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find all products belonging to a specific provider
    List<Product> findByProviderId(Long providerId);

}

