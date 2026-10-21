
package com.example.Fit.District.controller;

import com.example.Fit.District.entity.Subscription;
import com.example.Fit.District.service.SubscriptionService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class SubscriptionController {

        private final SubscriptionService subscriptionService;

        public SubscriptionController(
                        SubscriptionService subscriptionService) {
                this.subscriptionService = subscriptionService;
        }

        public record SubscriptionRequest(
                        @NotNull @Positive Long providerId) {
        }

        public record SubscriptionResponse(
                        Long subscriptionId,
                        Long customerId,
                        Long providerId,
                        LocalDate subscribeDate,
                        String status) {
        }

        private SubscriptionResponse toResponse(Subscription subscription) {
                return new SubscriptionResponse(
                                subscription.getSubscriptionId(),
                                subscription.getCustomer().getCustomerId(),
                                subscription.getProvider().getId(),
                                subscription.getSubscribeDate(),
                                subscription.getStatus());
        }

        // Subscribe a customer to a provider
        @PostMapping("/customers/{customerId}/subscriptions")
        public ResponseEntity<SubscriptionResponse> subscribe(
                        @PathVariable Long customerId,
                        @Valid @RequestBody SubscriptionRequest request) {

                Subscription subscription = subscriptionService.subscribe(
                                customerId, request.providerId());

                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(toResponse(subscription));
        }

        // View all subscriptions for a customer
        @GetMapping("/customers/{customerId}/subscriptions")
        public List<SubscriptionResponse> getSubscriptions(
                        @PathVariable Long customerId) {

                return subscriptionService
                                .getCustomerSubscriptions(customerId)
                                .stream()
                                .map(this::toResponse)
                                .toList();
        }

        // Delete a subscription
        @DeleteMapping("/subscriptions/{subscriptionId}")
        public ResponseEntity<Void> deleteSubscription(
                        @PathVariable Long subscriptionId) {

                subscriptionService.deleteSubscription(subscriptionId);

                return ResponseEntity.noContent().build();
        }
}
