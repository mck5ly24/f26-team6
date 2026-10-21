
package com.example.Fit.District.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "provider_replies")
public class ProviderReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @ManyToOne(optional = false)
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

    @Column(nullable = false, length = 2000)
    private String message;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public ProviderReply() {
    }

    @PrePersist
    public void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
