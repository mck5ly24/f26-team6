
package com.example.Fit.District.service;

import com.example.Fit.District.entity.Customer;
import com.example.Fit.District.entity.Provider;
import com.example.Fit.District.entity.Subscription;
import com.example.Fit.District.repository.CustomerRepository;
import com.example.Fit.District.repository.SubscriptionRepository;

import jakarta.persistence.EntityManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final CustomerRepository customerRepository;
    private final EntityManager entityManager;

    public SubscriptionService(
            SubscriptionRepository subscriptionRepository,
            CustomerRepository customerRepository,
            EntityManager entityManager) {

        this.subscriptionRepository = subscriptionRepository;
        this.customerRepository = customerRepository;
        this.entityManager = entityManager;
    }

    // Subscribe a customer to a provider
    public Subscription subscribe(Long customerId, Long providerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Customer not found"));

        if (providerId == null || providerId <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid provider ID");
        }

        Provider provider = entityManager.find(Provider.class, providerId);

        if (provider == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Provider not found");
        }

        if (subscriptionRepository
                .existsByCustomer_CustomerIdAndProvider_Id(
                        customerId, providerId)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Already subscribed");
        }

        Subscription subscription = new Subscription();
        subscription.setCustomer(customer);
        subscription.setProvider(provider);

        return subscriptionRepository.save(subscription);
    }

    // View subscriptions belonging to a customer
    public List<Subscription> getCustomerSubscriptions(Long customerId) {

        if (!customerRepository.existsById(customerId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Customer not found");
        }

        return subscriptionRepository
                .findByCustomer_CustomerId(customerId);
    }

    // Delete a subscription
    public void deleteSubscription(Long subscriptionId) {

        Subscription subscription = subscriptionRepository
                .findById(subscriptionId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Subscription not found"));

        subscriptionRepository.delete(subscription);
    }
}
