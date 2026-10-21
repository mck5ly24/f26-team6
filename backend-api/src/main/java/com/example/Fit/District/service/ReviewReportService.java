package com.example.Fit.District.service;

import com.example.Fit.District.entity.Customer;
import com.example.Fit.District.entity.Review;
import com.example.Fit.District.entity.ReviewReport;
import com.example.Fit.District.entity.ReviewReport.ReportStatus;

import com.example.Fit.District.repository.CustomerRepository;
import com.example.Fit.District.repository.ReviewRepository;
import com.example.Fit.District.repository.ReviewReportRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReviewReportService {

    private final ReviewReportRepository reportRepository;
    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;

    public ReviewReportService(
            ReviewReportRepository reportRepository,
            ReviewRepository reviewRepository,
            CustomerRepository customerRepository) {

        this.reportRepository = reportRepository;
        this.reviewRepository = reviewRepository;
        this.customerRepository = customerRepository;
    }

    // CUSTOMER: Report an inappropriate review
    public ReviewReport submitReport(
            Long customerId,
            Long reviewId,
            String reason) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Customer not found"));

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Review not found"));

        if (!"ACTIVE".equals(customer.getAccountStatus())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Customer account is not active");
        }

        if (reason == null || reason.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Report reason is required");
        }

        ReviewReport report = new ReviewReport();

        report.setReporter(customer);
        report.setReview(review);
        report.setReason(reason.trim());
        report.setStatus(ReportStatus.PENDING);

        return reportRepository.save(report);
    }

    // ADMIN: View all reports
    public List<ReviewReport> getAllReports() {
        return reportRepository.findAll();
    }

    // ADMIN: View reports awaiting review
    public List<ReviewReport> getPendingReports() {
        return reportRepository.findByStatus(ReportStatus.PENDING);
    }

    // ADMIN: View a specific report
    public ReviewReport getReportById(Long reportId) {
        return reportRepository.findById(reportId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Report not found"));
    }

    // ADMIN: Mark a report as resolved
    public ReviewReport resolveReport(Long reportId) {
        ReviewReport report = getReportById(reportId);

        report.setStatus(ReportStatus.RESOLVED);

        return reportRepository.save(report);
    }

    // ADMIN: Dismiss a report
    public ReviewReport dismissReport(Long reportId) {
        ReviewReport report = getReportById(reportId);

        report.setStatus(ReportStatus.DISMISSED);

        return reportRepository.save(report);
    }
}
