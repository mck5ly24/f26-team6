
package com.example.Fit.District.controller;

import com.example.Fit.District.entity.Provider;
import com.example.Fit.District.service.ProviderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
public class ProviderController {

    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    // CREATE: Add a provider
    @PostMapping
    public ResponseEntity<Provider> createProvider(
            @Valid @RequestBody Provider provider) {

        Provider createdProvider = providerService.createProvider(provider);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdProvider);
    }

    // READ: Get all providers
    @GetMapping
    public ResponseEntity<List<Provider>> getAllProviders() {
        return ResponseEntity.ok(providerService.getAllProviders());
    }

    // READ: Get a provider by ID
    @GetMapping("/{id}")
    public ResponseEntity<Provider> getProviderById(@PathVariable Long id) {
        return providerService.getProviderById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // UPDATE: Modify a provider
    @PutMapping("/{id}")
    public ResponseEntity<Provider> updateProvider(
            @PathVariable Long id,
            @Valid @RequestBody Provider provider) {

        return providerService.updateProvider(id, provider)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE: Remove a provider
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvider(@PathVariable Long id) {

        if (providerService.deleteProvider(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
