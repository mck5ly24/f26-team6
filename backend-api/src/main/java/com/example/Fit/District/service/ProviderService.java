
package com.example.Fit.District.service;

import com.example.Fit.District.entity.Provider;
import com.example.Fit.District.repository.ProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {

    private final ProviderRepository providerRepository;

    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    // CREATE: Save a new provider
    public Provider createProvider(Provider provider) {
        provider.setId(null);
        return providerRepository.save(provider);
    }

    // READ: Get all providers
    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    // READ: Get a provider by ID
    public Optional<Provider> getProviderById(Long id) {
        return providerRepository.findById(id);
    }

    // UPDATE: Modify an existing provider
    public Optional<Provider> updateProvider(Long id, Provider updatedProvider) {
        return providerRepository.findById(id).map(provider -> {
            provider.setBusinessName(updatedProvider.getBusinessName());
            provider.setContactName(updatedProvider.getContactName());
            provider.setEmail(updatedProvider.getEmail());
            provider.setPhone(updatedProvider.getPhone());
            provider.setDescription(updatedProvider.getDescription());

            return providerRepository.save(provider);
        });
    }

    // DELETE: Remove a provider
    public boolean deleteProvider(Long id) {
        if (!providerRepository.existsById(id)) {
            return false;
        }

        providerRepository.deleteById(id);
        return true;
    }
}
