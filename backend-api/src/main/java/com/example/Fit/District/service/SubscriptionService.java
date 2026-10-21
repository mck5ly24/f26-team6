
package com.example.Fit.District.service;

import com.example.Fit.District.entity.Customer;
import com.example.Fit.District.entity.Subscription;
import com.example.Fit.District.repository.CustomerRepository;
import com.example.Fit.District.repository.SubscriptionRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final CustomerRepository customerRepository;

    public SubscriptionService(
            SubscriptionRepository subscriptionRepository,
            CustomerRepository customerRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.customerRepository = customerRepository;
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

        if (subscriptionRepository
                .existsByCustomer_CustomerIdAndProviderId(
                        customerId, providerId)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Already subscribed");
        }

        Subscription subscription = new Subscription();
        subscription.setCustomer(customer);
        subscription.setProviderId(providerId);

        return subscriptionRepository.save(subscription);
    }

    // View all subscriptions for a customer
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
