package com.example.Fit.District.controller;

import com.example.Fit.District.service.ProviderCustomerActivityService;
import com.example.Fit.District.service.ProviderCustomerActivityService.CustomerActivityResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
public class ProviderCustomerActivityController {

    private final ProviderCustomerActivityService activityService;

    public ProviderCustomerActivityController(
            ProviderCustomerActivityService activityService) {
        this.activityService = activityService;
    }

    // PROVIDER: View customer review activity for their products
    @GetMapping("/{providerId}/customer-activity")
    public ResponseEntity<List<CustomerActivityResponse>>
            getCustomerActivity(@PathVariable Long providerId) {

        return ResponseEntity.ok(
                activityService.getCustomerActivity(providerId)
        );
    }
}
