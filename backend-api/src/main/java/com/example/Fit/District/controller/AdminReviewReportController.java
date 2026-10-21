package com.example.Fit.District.controller;

import com.example.Fit.District.entity.ReviewReport;
import com.example.Fit.District.service.ReviewReportService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/review-reports")
public class AdminReviewReportController {

    private final ReviewReportService reviewReportService;

    public AdminReviewReportController(
            ReviewReportService reviewReportService) {
        this.reviewReportService = reviewReportService;
    }

    // ADMIN: View all submitted reports
    @GetMapping
    public ResponseEntity<List<ReviewReport>> getAllReports() {
        return ResponseEntity.ok(
                reviewReportService.getAllReports()
        );
    }

    // ADMIN: View pending reports
    @GetMapping("/pending")
    public ResponseEntity<List<ReviewReport>> getPendingReports() {
        return ResponseEntity.ok(
                reviewReportService.getPendingReports()
        );
    }

    // ADMIN: View one report
    @GetMapping("/{id}")
    public ResponseEntity<ReviewReport> getReportById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                reviewReportService.getReportById(id)
        );
    }

    // ADMIN: Mark a report as resolved
    @PutMapping("/{id}/resolve")
    public ResponseEntity<ReviewReport> resolveReport(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                reviewReportService.resolveReport(id)
        );
    }

    // ADMIN: Dismiss a report
    @PutMapping("/{id}/dismiss")
    public ResponseEntity<ReviewReport> dismissReport(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                reviewReportService.dismissReport(id)
        );
    }
}

