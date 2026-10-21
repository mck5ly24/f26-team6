package com.example.Fit.District.repository;

import com.example.Fit.District.entity.Review;
import com.example.Fit.District.entity.Review.ModerationStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Find reviews awaiting moderation
    List<Review> findByModerationStatus(
            ModerationStatus moderationStatus
    );

    // Find all reviews for a product
    List<Review> findByProductId(Long productId);

    // Find all reviews submitted by a customer
    List<Review> findByCustomerCustomerId(Long customerId);
}
