package com.example.Fit.District.repository;

import com.example.Fit.District.entity.ReviewReport;
import com.example.Fit.District.entity.ReviewReport.ReportStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewReportRepository
        extends JpaRepository<ReviewReport, Long> {

    // Find reports awaiting administrator review
    List<ReviewReport> findByStatus(ReportStatus status);

    // Find reports associated with a review
    List<ReviewReport> findByReviewId(Long reviewId);

    // Find reports submitted by a customer
    List<ReviewReport> findByReporterCustomerId(Long customerId);
}
