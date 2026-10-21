package com.example.Fit.District.controller;

import com.example.Fit.District.entity.Review;
import com.example.Fit.District.service.ReviewService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // CUSTOMER: Submit a product review
    @PostMapping("/customer/{customerId}/product/{productId}")
    public ResponseEntity<Review> createReview(
            @PathVariable Long customerId,
            @PathVariable Long productId,
            @RequestBody Review review) {

        Review createdReview = reviewService.createReview(
                customerId,
                productId,
                review
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdReview);
    }

    // PUBLIC: View only approved product reviews
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getApprovedReviews(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                reviewService.getApprovedReviews(productId)
        );
    }
}
