package com.example.Fit.District.controller;

import com.example.Fit.District.entity.ProviderReply;
import com.example.Fit.District.service.ProviderReplyService;
import com.example.Fit.District.service.ProviderAuthorizationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/provider-replies")
public class ProviderReplyController {

    private final ProviderReplyService replyService;
    private final ProviderAuthorizationService authorizationService;

    public ProviderReplyController(
            ProviderReplyService replyService,
            ProviderAuthorizationService authorizationService) {

        this.replyService = replyService;
        this.authorizationService = authorizationService;
    }

    // PROVIDER: Reply to a review on their own product
    @PostMapping("/provider/{providerId}/review/{reviewId}")
    public ResponseEntity<ProviderReplyResponse> createReply(
            @PathVariable Long providerId,
            @PathVariable Long reviewId,
            @RequestBody ReplyRequest request,
            Authentication authentication) {

        authorizationService.verifyProviderAccess(
                providerId, authentication
        );

        ProviderReply reply = replyService.createReply(
                providerId,
                reviewId,
                request.message()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toResponse(reply));
    }

    // Retrieve replies associated with a review
    @GetMapping("/review/{reviewId}")
    public ResponseEntity<List<ProviderReplyResponse>> getRepliesByReview(
            @PathVariable Long reviewId) {

        List<ProviderReplyResponse> replies =
                replyService.getRepliesByReview(reviewId)
                        .stream()
                        .map(this::toResponse)
                        .toList();

        return ResponseEntity.ok(replies);
    }

    // PROVIDER: Retrieve replies written by their account
    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<ProviderReplyResponse>> getRepliesByProvider(
            @PathVariable Long providerId,
            Authentication authentication) {

        authorizationService.verifyProviderAccess(
                providerId, authentication
        );

        List<ProviderReplyResponse> replies =
                replyService.getRepliesByProvider(providerId)
                        .stream()
                        .map(this::toResponse)
                        .toList();

        return ResponseEntity.ok(replies);
    }

    private ProviderReplyResponse toResponse(ProviderReply reply) {
        return new ProviderReplyResponse(
                reply.getId(),
                reply.getReview().getId(),
                reply.getProvider().getId(),
                reply.getMessage(),
                reply.getCreatedAt()
        );
    }

    public record ReplyRequest(String message) {}

    public record ProviderReplyResponse(
            Long id,
            Long reviewId,
            Long providerId,
            String message,
            LocalDateTime createdAt
    ) {}
}
