
package com.example.Fit.District.controller;

import com.example.Fit.District.entity.Product;
import com.example.Fit.District.service.AdminProductService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    private final AdminProductService adminProductService;

    public AdminProductController(
            AdminProductService adminProductService) {
        this.adminProductService = adminProductService;
    }

    // READ: View products awaiting approval
    @GetMapping("/pending")
    public ResponseEntity<List<Product>> getPendingProducts() {
        return ResponseEntity.ok(
                adminProductService.getPendingProducts());
    }

    // UPDATE: Approve a product
    @PutMapping("/{id}/approve")
    public ResponseEntity<Product> approveProduct(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                adminProductService.approveProduct(id));
    }

    // UPDATE: Reject a product
    @PutMapping("/{id}/reject")
    public ResponseEntity<Product> rejectProduct(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                adminProductService.rejectProduct(id));
    }
}
