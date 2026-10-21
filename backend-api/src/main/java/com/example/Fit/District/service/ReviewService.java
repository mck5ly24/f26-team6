package com.example.Fit.District.service;

import com.example.Fit.District.entity.Customer;
import com.example.Fit.District.entity.Product;
import com.example.Fit.District.entity.Review;
import com.example.Fit.District.repository.CustomerRepository;
import com.example.Fit.District.repository.ProductRepository;
import com.example.Fit.District.repository.ReviewRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public ReviewService(
            ReviewRepository reviewRepository,
            CustomerRepository customerRepository,
            ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    // CUSTOMER: Submit a review for a product
    public Review createReview(
            Long customerId,
            Long productId,
            Review review) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Customer not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Product not found"));

        if (review.getRating() < 1 || review.getRating() > 5) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Rating must be between 1 and 5");
        }

        if (review.getComment() == null ||
                review.getComment().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Review comment cannot be empty");
        }

        review.setId(null);
        review.setCustomer(customer);
        review.setProduct(product);
        review.setCreatedAt(null);

        // Customers cannot approve their own reviews
        review.setModerationStatus(
                Review.ModerationStatus.PENDING);

        return reviewRepository.save(review);
    }

    // PUBLIC: Only show approved reviews for a product
    public List<Review> getApprovedReviews(Long productId) {

        if (!productRepository.existsById(productId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Product not found");
        }

        return reviewRepository.findByProductId(productId)
                .stream()
                .filter(review ->
                        review.getModerationStatus()
                                == Review.ModerationStatus.APPROVED)
                .toList();
    }
}
