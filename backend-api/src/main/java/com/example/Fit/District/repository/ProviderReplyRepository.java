
package com.example.Fit.District.repository;

import com.example.Fit.District.entity.ProviderReply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderReplyRepository
        extends JpaRepository<ProviderReply, Long> {

    // Find all provider replies to a specific customer review
    List<ProviderReply> findByReviewId(Long reviewId);

    // Find all replies submitted by a provider
    List<ProviderReply> findByProviderId(Long providerId);

    // Check whether a provider has already replied to a review
    boolean existsByReviewIdAndProviderId(
            Long reviewId,
            Long providerId
    );
}
