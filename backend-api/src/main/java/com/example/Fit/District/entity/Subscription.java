
package com.example.Fit.District.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // Provider relationship will be added after
    // your partner merges Provider.java into main.

    private Long providerId;

    private LocalDate subscribeDate;

    @Column(nullable = false)
    private String status = "ACTIVE";

    public Subscription() {
    }

    @PrePersist
    public void onCreate() {
        if (subscribeDate == null) {
            subscribeDate = LocalDate.now();
        }
    }

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public LocalDate getSubscribeDate() {
        return subscribeDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
