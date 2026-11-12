package com.example.Fit.District.controller;

import com.example.Fit.District.entity.Provider;
import com.example.Fit.District.service.ProductService;
import com.example.Fit.District.service.ProviderAuthorizationService;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.Fit.District.entity.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/provider")
public class ProviderMvcController {

    private final ProductService productService;
    private final ProviderAuthorizationService providerAuthorizationService;

    public ProviderMvcController(
            ProductService productService,
            ProviderAuthorizationService providerAuthorizationService) {

        this.productService = productService;
        this.providerAuthorizationService = providerAuthorizationService;
    }

    @GetMapping("/products")
    public String showProducts(Model model, Authentication authentication) {

        Provider provider = providerAuthorizationService.getAuthenticatedProvider(authentication);

        model.addAttribute(
                "products",
                productService.getProductsByProvider(provider.getId()));

        return "provider-products";
    }

    @PostMapping("/products")
    public String saveProduct(
            @ModelAttribute Product product,
            Authentication authentication,
            RedirectAttributes redirectAttributes) {

        Provider provider = providerAuthorizationService.getAuthenticatedProvider(authentication);

        productService.createProduct(provider.getId(), product);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Product added successfully!");

        return "redirect:/provider/products";
    }

    @PostMapping("/products/{id}/edit")
    public String updateProduct(
            @PathVariable Long id,
            @ModelAttribute Product updatedProduct,
            Authentication authentication,
            RedirectAttributes redirectAttributes) {

        Provider provider = providerAuthorizationService.getAuthenticatedProvider(authentication);

        Product existingProduct = productService.getProductById(id);

        // Prevent providers from editing another provider's products.
        if (!existingProduct.getProvider().getId().equals(provider.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You cannot edit another provider's product");
        }

        productService.updateProduct(id, updatedProduct);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Product updated successfully!");

        return "redirect:/provider/products";
    }

    @GetMapping("/products/{id}/edit")
    public String showEditProduct(
            @PathVariable Long id,
            Authentication authentication,
            Model model) {

        // Identify the logged-in provider.
        Provider provider = providerAuthorizationService.getAuthenticatedProvider(authentication);

        // Retrieve the product from the database.
        Product product = productService.getProductById(id);

        // Make sure this product belongs to the logged-in provider.
        if (!product.getProvider().getId().equals(provider.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You cannot edit another provider's product");
        }

        // Send the existing product information to FreeMarker.
        model.addAttribute("product", product);

        return "provider-product-edit";
    }

    @PostMapping("/products/{id}/delete")
    public String deleteProduct(
            @PathVariable Long id,
            Authentication authentication,
            RedirectAttributes redirectAttributes) {

        // Identify the logged-in provider.
        Provider provider = providerAuthorizationService.getAuthenticatedProvider(authentication);

        // Find the product being deleted.
        Product product = productService.getProductById(id);

        // Make sure the product belongs to this provider.
        if (!product.getProvider().getId().equals(provider.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You cannot delete another provider's product");
        }

        // Delete the product from the database.
        productService.deleteProduct(id);

        // Display a success message.
        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Product deleted successfully!");

        // Return to Manage Products.
        return "redirect:/provider/products";
    }
}
