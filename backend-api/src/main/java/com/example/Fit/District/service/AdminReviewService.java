
package com.example.Fit.District.service;

import com.example.Fit.District.entity.Review;
import com.example.Fit.District.entity.Review.ModerationStatus;
import com.example.Fit.District.repository.ReviewRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AdminReviewService {

    private final ReviewRepository reviewRepository;

    public AdminReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // ADMIN: View all customer reviews
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // ADMIN: View reviews awaiting moderation
    public List<Review> getPendingReviews() {
        return reviewRepository.findByModerationStatus(
                ModerationStatus.PENDING
        );
    }

    // ADMIN: View one review
    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Review not found"
                        ));
    }

    // ADMIN: Approve a review
    public Review approveReview(Long reviewId) {
        Review review = getReviewById(reviewId);

        review.setModerationStatus(ModerationStatus.APPROVED);

        return reviewRepository.save(review);
    }

    // ADMIN: Remove inappropriate or fraudulent content
    public Review removeReview(Long reviewId) {
        Review review = getReviewById(reviewId);

        review.setModerationStatus(ModerationStatus.REMOVED);

        return reviewRepository.save(review);
    }
}
