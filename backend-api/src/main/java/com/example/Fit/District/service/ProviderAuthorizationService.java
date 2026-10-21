package com.example.Fit.District.service;

import com.example.Fit.District.entity.Provider;
import com.example.Fit.District.repository.ProviderRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProviderAuthorizationService {

    private final ProviderRepository providerRepository;
    private final String providerUsername;
    private final String providerEmail;

    public ProviderAuthorizationService(
            ProviderRepository providerRepository,
            @Value("${APP_PROVIDER_USERNAME}") String providerUsername,
            @Value("${APP_PROVIDER_EMAIL}") String providerEmail) {

        this.providerRepository = providerRepository;
        this.providerUsername = providerUsername;
        this.providerEmail = providerEmail;
    }

    public void verifyProviderAccess(
            Long requestedProviderId,
            Authentication authentication) {

        if (authentication == null ||
                !authentication.isAuthenticated()) {

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Authentication required"
            );
        }

        boolean isProvider = authentication.getAuthorities()
                .stream()
                .anyMatch(authority ->
                        authority.getAuthority().equals("ROLE_PROVIDER")
                );

        if (!isProvider) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Provider account required"
            );
        }

        // Match the authenticated development login
        if (!authentication.getName().equals(providerUsername)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Provider identity mismatch"
            );
        }

        // Find the database account associated with this login
        Provider provider = providerRepository.findByEmail(providerEmail)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.FORBIDDEN,
                        "No provider account linked to this login"
                ));

        // Prevent one provider from accessing another provider's account
        if (!provider.getId().equals(requestedProviderId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You cannot access another provider's account"
            );
        }
    }
}
