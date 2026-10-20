
package com.example.Fit.District.controller;

import com.example.Fit.District.entity.Product;
import com.example.Fit.District.service.ProductService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // CREATE: Add a product for a provider
    @PostMapping("/provider/{providerId}")
    public ResponseEntity<Product> createProduct(
            @PathVariable Long providerId,
            @Valid @RequestBody Product product) {

        Product createdProduct =
                productService.createProduct(providerId, product);

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

    // UPDATE: Modify a product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody Product product) {

        return ResponseEntity.ok(
                productService.updateProduct(id, product));
    }

    // DELETE: Remove a product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id) {

        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }
}
