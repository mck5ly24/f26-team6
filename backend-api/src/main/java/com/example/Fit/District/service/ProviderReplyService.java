package com.example.Fit.District.service;

import com.example.Fit.District.entity.Provider;
import com.example.Fit.District.entity.ProviderReply;
import com.example.Fit.District.entity.Review;

import com.example.Fit.District.repository.ProviderRepository;
import com.example.Fit.District.repository.ProviderReplyRepository;
import com.example.Fit.District.repository.ReviewRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProviderReplyService {

    private final ProviderReplyRepository replyRepository;
    private final ProviderRepository providerRepository;
    private final ReviewRepository reviewRepository;

    public ProviderReplyService(
            ProviderReplyRepository replyRepository,
            ProviderRepository providerRepository,
            ReviewRepository reviewRepository) {

        this.replyRepository = replyRepository;
        this.providerRepository = providerRepository;
        this.reviewRepository = reviewRepository;
    }

    // PROVIDER: Reply to a customer review
    public ProviderReply createReply(
            Long providerId,
            Long reviewId,
            String message) {

        Provider provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Provider not found"
                ));

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Review not found"
                ));

        // Make sure the review belongs to this provider's product
        if (!review.getProduct().getProvider().getId()
                .equals(providerId)) {

            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "This review does not belong to your product"
            );
        }

        // Providers can only reply to approved reviews
        if (review.getModerationStatus()
                != Review.ModerationStatus.APPROVED) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Only approved reviews can receive replies"
            );
        }

        // Prevent blank replies
        if (message == null || message.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Reply message cannot be empty"
            );
        }

        if (message.length() > 2000) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Reply message must be 2000 characters or fewer"
            );
        }

        // Limit each provider to one reply per review
        if (replyRepository.existsByReviewIdAndProviderId(
                reviewId, providerId)) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Provider has already replied to this review"
            );
        }

        ProviderReply reply = new ProviderReply();

        reply.setProvider(provider);
        reply.setReview(review);
        reply.setMessage(message.trim());

        return replyRepository.save(reply);
    }

    // Retrieve replies associated with a review
    public List<ProviderReply> getRepliesByReview(Long reviewId) {

        if (!reviewRepository.existsById(reviewId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Review not found"
            );
        }

        return replyRepository.findByReviewId(reviewId);
    }

    // Retrieve all replies written by a provider
    public List<ProviderReply> getRepliesByProvider(Long providerId) {

        if (!providerRepository.existsById(providerId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Provider not found"
            );
        }

        return replyRepository.findByProviderId(providerId);
    }
}
