package com.example.Fit.District.controller;

import com.example.Fit.District.service.ProviderCustomerActivityService;
import com.example.Fit.District.service.ProviderCustomerActivityService.CustomerActivityResponse;
import com.example.Fit.District.service.ProviderAuthorizationService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
public class ProviderCustomerActivityController {

    private final ProviderCustomerActivityService activityService;
    private final ProviderAuthorizationService authorizationService;

    public ProviderCustomerActivityController(
            ProviderCustomerActivityService activityService,
            ProviderAuthorizationService authorizationService) {

        this.activityService = activityService;
        this.authorizationService = authorizationService;
    }

    // PROVIDER: View customer activity for their own products
    @GetMapping("/{providerId}/customer-activity")
    public ResponseEntity<List<CustomerActivityResponse>> getCustomerActivity(
            @PathVariable Long providerId,
            Authentication authentication) {

        // Verify authenticated provider owns this account
        authorizationService.verifyProviderAccess(
                providerId,
                authentication);

        return ResponseEntity.ok(
                activityService.getCustomerActivity(providerId));
    }
}
