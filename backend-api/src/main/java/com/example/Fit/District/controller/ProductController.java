
package com.example.Fit.District.controller;

import com.example.Fit.District.entity.Product;
import com.example.Fit.District.service.ProductService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Fit.District.entity.Provider;
import com.example.Fit.District.service.ProviderAuthorizationService;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProviderAuthorizationService providerAuthorizationService;

    public ProductController(
            ProductService productService,
            ProviderAuthorizationService providerAuthorizationService) {

        this.productService = productService;
        this.providerAuthorizationService = providerAuthorizationService;
    }

    // CREATE: Add a product for a provider
    @PostMapping("/provider/{providerId}")
    public ResponseEntity<Product> createProduct(
            @PathVariable Long providerId,
            @Valid @RequestBody Product product,
            Authentication authentication) {

        Provider provider = providerAuthorizationService.getAuthenticatedProvider(authentication);

        if (!provider.getId().equals(providerId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You cannot create products for another provider");
        }

        Product createdProduct = productService.createProduct(provider.getId(), product);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdProduct);
    }

    // READ: Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // READ: Get a product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                productService.getProductById(id));
    }

    // READ: Get products belonging to a provider
    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<Product>> getProductsByProvider(
            @PathVariable Long providerId) {

        return ResponseEntity.ok(
                productService.getProductsByProvider(providerId));
    }

    // UPDATE: Modify a product owned by the logged-in provider
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody Product product,
            Authentication authentication) {

        // Get the logged-in provider
        Provider provider = providerAuthorizationService.getAuthenticatedProvider(authentication);

        // Find the existing product
        Product existingProduct = productService.getProductById(id);

        // Check that this provider owns the product
        if (!existingProduct.getProvider().getId().equals(provider.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You cannot update another provider's product");
        }

        // Save the updated product
        Product updatedProduct = productService.updateProduct(id, product);

        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE: Remove a product owned by the logged-in provider
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id,
            Authentication authentication) {

        // Identify the logged-in provider
        Provider provider = providerAuthorizationService.getAuthenticatedProvider(authentication);

        // Find the product
        Product existingProduct = productService.getProductById(id);

        // Verify ownership
        if (!existingProduct.getProvider().getId().equals(provider.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You cannot delete another provider's product");
        }

        // Delete the product from Neon PostgreSQL
        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }
}
