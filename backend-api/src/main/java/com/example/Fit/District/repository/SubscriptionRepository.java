
package com.example.Fit.District.repository;

import com.example.Fit.District.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository
        extends JpaRepository<Subscription, Long> {

    List<Subscription> findByCustomer_CustomerId(Long customerId);

    boolean existsByCustomer_CustomerIdAndProviderId(
            Long customerId, Long providerId);
}
