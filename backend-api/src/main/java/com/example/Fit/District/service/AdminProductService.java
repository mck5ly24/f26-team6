
package com.example.Fit.District.service;

import com.example.Fit.District.entity.Product;
import com.example.Fit.District.entity.Product.ModerationStatus;
import com.example.Fit.District.repository.ProductRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AdminProductService {

    private final ProductRepository productRepository;

    public AdminProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products awaiting administrator review
    public List<Product> getPendingProducts() {
        return productRepository.findAll()
                .stream()
                .filter(product ->
                        product.getModerationStatus()
                                == ModerationStatus.PENDING)
                .toList();
    }

    // Approve a product
    public Product approveProduct(Long productId) {
        Product product = findProduct(productId);

        product.setModerationStatus(ModerationStatus.APPROVED);

        return productRepository.save(product);
    }

    // Reject a product
    public Product rejectProduct(Long productId) {
        Product product = findProduct(productId);

        product.setModerationStatus(ModerationStatus.REJECTED);

        return productRepository.save(product);
    }

    // Get a product or return 404
    private Product findProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Product not found"));
    }
}

