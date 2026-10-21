
package com.example.Fit.District.service;

import com.example.Fit.District.entity.Customer;
import com.example.Fit.District.repository.CustomerRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AdminCustomerService {

    private final CustomerRepository customerRepository;

    public AdminCustomerService(
            CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // ADMIN: View all customer accounts
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // ADMIN: View a specific customer
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Customer not found"
                        ));
    }

    // ADMIN: Suspend a customer account
    public Customer suspendCustomer(Long customerId) {
        Customer customer = getCustomerById(customerId);

        customer.setAccountStatus("SUSPENDED");

        return customerRepository.save(customer);
    }

    // ADMIN: Reactivate a customer account
    public Customer activateCustomer(Long customerId) {
        Customer customer = getCustomerById(customerId);

        customer.setAccountStatus("ACTIVE");

        return customerRepository.save(customer);
    }
}

