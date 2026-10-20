
package com.example.Fit.District.service;

import com.example.Fit.District.entity.Product;
import com.example.Fit.District.entity.Provider;
import com.example.Fit.District.repository.ProductRepository;
import com.example.Fit.District.repository.ProviderRepository;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProviderRepository providerRepository;

    public ProductService(
            ProductRepository productRepository,
            ProviderRepository providerRepository) {
        this.productRepository = productRepository;
        this.providerRepository = providerRepository;
    }

    // CREATE: Add a product to an existing provider
    public Product createProduct(Long providerId, Product product) {
        Provider provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Provider not found"));

        product.setId(null);
        product.setProvider(provider);

        // SECURITY: Every new product must await admin approval
        product.setModerationStatus(
                Product.ModerationStatus.PENDING
        );

        return productRepository.save(product);
    }

    // READ: Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // READ: Get one product
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Product not found"));
    }

    // READ: Get products belonging to one provider
    public List<Product> getProductsByProvider(Long providerId) {
        if (!providerRepository.existsById(providerId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Provider not found");
        }

        return productRepository.findByProviderId(providerId);
    }

    // UPDATE: Modify product information
    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = getProductById(id);

        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setSize(updatedProduct.getSize());
        product.setImageUrl(updatedProduct.getImageUrl());
        product.setStockQuantity(updatedProduct.getStockQuantity());

        // Preserve existing moderation status.
        // Providers cannot change approval status through this method.

        return productRepository.save(product);
    }

    // DELETE: Remove a product
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}
