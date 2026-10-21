package com.example.Fit.District.controller;

import com.example.Fit.District.entity.Review;
import com.example.Fit.District.service.AdminReviewService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/reviews")
public class AdminReviewController {

    private final AdminReviewService adminReviewService;

    public AdminReviewController(
            AdminReviewService adminReviewService) {
        this.adminReviewService = adminReviewService;
    }

    // ADMIN: View all customer reviews
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok(
                adminReviewService.getAllReviews()
        );
    }

    // ADMIN: View reviews awaiting moderation
    @GetMapping("/pending")
    public ResponseEntity<List<Review>> getPendingReviews() {
        return ResponseEntity.ok(
                adminReviewService.getPendingReviews()
        );
    }

    // ADMIN: View one customer review
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                adminReviewService.getReviewById(id)
        );
    }

    // ADMIN: Approve a review
    @PutMapping("/{id}/approve")
    public ResponseEntity<Review> approveReview(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                adminReviewService.approveReview(id)
        );
    }

    // ADMIN: Remove a review
    @PutMapping("/{id}/remove")
    public ResponseEntity<Review> removeReview(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                adminReviewService.removeReview(id)
        );
    }
}

