package com.example.Fit.District.service;

import com.example.Fit.District.entity.Review;
import com.example.Fit.District.repository.ProviderRepository;
import com.example.Fit.District.repository.ReviewRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProviderCustomerActivityService {

    private final ReviewRepository reviewRepository;
    private final ProviderRepository providerRepository;

    public ProviderCustomerActivityService(
            ReviewRepository reviewRepository,
            ProviderRepository providerRepository) {

        this.reviewRepository = reviewRepository;
        this.providerRepository = providerRepository;
    }

    public List<CustomerActivityResponse> getCustomerActivity(
            Long providerId) {

        if (!providerRepository.existsById(providerId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Provider not found"
            );
        }

        return reviewRepository.findByProductProviderId(providerId)
                .stream()
                .filter(review ->
                        review.getModerationStatus()
                                == Review.ModerationStatus.APPROVED
                )
                .map(review -> new CustomerActivityResponse(
                        review.getId(),
                        review.getCustomer().getCustomerId(),
                        review.getCustomer().getFullName(),
                        review.getProduct().getId(),
                        review.getProduct().getName(),
                        review.getRating(),
                        review.getComment(),
                        review.getCreatedAt()
                ))
                .toList();
    }

    public record CustomerActivityResponse(
            Long reviewId,
            Long customerId,
            String customerName,
            Long productId,
            String productName,
            int rating,
            String comment,
            LocalDateTime reviewDate
    ) {}
}

