package com.example.Fit.District.controller;

import com.example.Fit.District.entity.ReviewReport;
import com.example.Fit.District.service.ReviewReportService;

import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review-reports")
public class ReviewReportController {

    private final ReviewReportService reviewReportService;

    public ReviewReportController(
            ReviewReportService reviewReportService) {
        this.reviewReportService = reviewReportService;
    }

    // CUSTOMER: Submit a report about a review
    @PostMapping("/customer/{customerId}/review/{reviewId}")
    public ResponseEntity<ReviewReport> submitReport(
            @PathVariable Long customerId,
            @PathVariable Long reviewId,
            @RequestBody ReportRequest request) {

        ReviewReport report = reviewReportService.submitReport(
                customerId,
                reviewId,
                request.reason()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(report);
    }

    public record ReportRequest(
            @NotBlank String reason
    ) {}
}

