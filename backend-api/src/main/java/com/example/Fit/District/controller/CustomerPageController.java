
package com.example.Fit.District.controller;

import com.example.Fit.District.entity.Customer;
import com.example.Fit.District.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/customers")
public class CustomerPageController {

    private final CustomerService customerService;

    public CustomerPageController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Display customer registration page
    @GetMapping("/register")
    public String showRegistration() {
        return "customer-register";
    }

    // Create a new customer in the database
    @PostMapping("/register")
    public String registerCustomer(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String stylePreference,
            RedirectAttributes redirectAttributes) {

        Customer customer = new Customer();
        customer.setFullName(fullName);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customer.setStylePreference(stylePreference);

        try {
            Customer saved = customerService.createCustomer(customer);

            redirectAttributes.addFlashAttribute(
                    "success", "Customer account created successfully!"
            );

            return "redirect:/customers/profile/" + saved.getCustomerId();

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                    "error", "Unable to create customer. Check your information."
            );

            return "redirect:/customers/register";
        }
    }

    // Display customer profile using real database data
    @GetMapping("/profile/{id}")
    public String showProfile(
            @PathVariable Long id,
            Model model) {

        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);

        return "customer-profile";
    }

    // Update an existing customer profile
    @PostMapping("/profile/{id}")
    public String updateProfile(
            @PathVariable Long id,
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String stylePreference,
            RedirectAttributes redirectAttributes) {

        try {
            Customer updated = new Customer();
            updated.setFullName(fullName);
            updated.setEmail(email);
            updated.setPhoneNumber(phoneNumber);
            updated.setStylePreference(stylePreference);

            customerService.updateCustomer(id, updated);

            redirectAttributes.addFlashAttribute(
                    "success", "Profile updated successfully!"
            );

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                    "error", "Unable to update profile."
            );
        }

        return "redirect:/customers/profile/" + id;
    }
}
