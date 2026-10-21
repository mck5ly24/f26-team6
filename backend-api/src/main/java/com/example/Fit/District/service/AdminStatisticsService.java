
package com.example.Fit.District.service;

import com.example.Fit.District.entity.Product;
import com.example.Fit.District.repository.ProductRepository;
import com.example.Fit.District.repository.ProviderRepository;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminStatisticsService {

    private final ProductRepository productRepository;
    private final ProviderRepository providerRepository;

    public AdminStatisticsService(
            ProductRepository productRepository,
            ProviderRepository providerRepository) {
        this.productRepository = productRepository;
        this.providerRepository = providerRepository;
    }

    public Map<String, Long> getPlatformStatistics() {

        Map<String, Long> statistics = new HashMap<>();

        List<Product> products = productRepository.findAll();

        long pendingProducts = products.stream()
                .filter(product ->
                        product.getModerationStatus()
                                == Product.ModerationStatus.PENDING)
                .count();

        long approvedProducts = products.stream()
                .filter(product ->
                        product.getModerationStatus()
                                == Product.ModerationStatus.APPROVED)
                .count();

        long rejectedProducts = products.stream()
                .filter(product ->
                        product.getModerationStatus()
                                == Product.ModerationStatus.REJECTED)
                .count();

        statistics.put(
                "totalProviders",
                providerRepository.count()
        );

        statistics.put(
                "totalProducts",
                productRepository.count()
        );

        statistics.put("pendingProducts", pendingProducts);
        statistics.put("approvedProducts", approvedProducts);
        statistics.put("rejectedProducts", rejectedProducts);

        return statistics;
    }
}
