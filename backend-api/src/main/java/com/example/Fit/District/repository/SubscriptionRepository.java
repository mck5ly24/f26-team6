
package com.example.Fit.District.repository;

import com.example.Fit.District.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository
        extends JpaRepository<Subscription, Long> {

    // Get all subscriptions belonging to a customer
    List<Subscription> findByCustomer_CustomerId(Long customerId);

    // Check if a customer is already subscribed to a provider
    boolean existsByCustomer_CustomerIdAndProvider_Id(
            Long customerId, Long providerId);
}
