
package com.example.Fit.District.controller;

import com.example.Fit.District.service.AdminStatisticsService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/statistics")
public class AdminStatisticsController {

    private final AdminStatisticsService adminStatisticsService;

    public AdminStatisticsController(
            AdminStatisticsService adminStatisticsService) {
        this.adminStatisticsService = adminStatisticsService;
    }

    // GET: View platform usage statistics
    @GetMapping
    public ResponseEntity<Map<String, Long>> getStatistics() {
        return ResponseEntity.ok(
                adminStatisticsService.getPlatformStatistics()
        );
    }
}

