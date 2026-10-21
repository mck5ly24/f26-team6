
package com.example.Fit.District.controller;

import com.example.Fit.District.entity.Customer;
import com.example.Fit.District.service.AdminCustomerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/customers")
public class AdminCustomerController {

    private final AdminCustomerService adminCustomerService;

    public AdminCustomerController(
            AdminCustomerService adminCustomerService) {
        this.adminCustomerService = adminCustomerService;
    }

    // ADMIN: View all customer accounts
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(
                adminCustomerService.getAllCustomers()
        );
    }

    // ADMIN: View one customer account
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                adminCustomerService.getCustomerById(id)
        );
    }

    // ADMIN: Suspend a customer account
    @PutMapping("/{id}/suspend")
    public ResponseEntity<Customer> suspendCustomer(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                adminCustomerService.suspendCustomer(id)
        );
    }

    // ADMIN: Reactivate a customer account
    @PutMapping("/{id}/activate")
    public ResponseEntity<Customer> activateCustomer(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                adminCustomerService.activateCustomer(id)
        );
    }
}

