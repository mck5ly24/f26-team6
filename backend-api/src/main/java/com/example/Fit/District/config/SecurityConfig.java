package com.example.Fit.District.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import com.example.Fit.District.entity.Provider;
import com.example.Fit.District.service.ProviderAuthorizationService;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/**"))

                .authorizeHttpRequests(auth -> auth

                        // Administrator endpoints
                        .requestMatchers("/api/admin/**")
                        .hasRole("ADMIN")

                        // Only providers or admins may submit provider replies
                        .requestMatchers(
                                org.springframework.http.HttpMethod.POST,
                                "/api/provider-replies/provider/*/review/*")
                        .hasAnyRole("PROVIDER", "ADMIN")

                        // Provider reply reads require authentication
                        .requestMatchers("/api/provider-replies/**")
                        .authenticated()

                        // Provider portal requires provider login
                        .requestMatchers("/provider/**")
                        .hasRole("PROVIDER")

                        // Only administrators can create, update, or delete providers
                        .requestMatchers(
                                org.springframework.http.HttpMethod.POST,
                                "/api/providers", "/api/providers/")
                        .hasRole("ADMIN")

                        .requestMatchers(
                                org.springframework.http.HttpMethod.PUT,
                                "/api/providers/*")
                        .hasRole("ADMIN")

                        .requestMatchers(
                                org.springframework.http.HttpMethod.DELETE,
                                "/api/providers/*")
                        .hasRole("ADMIN")

                        // Provider information remains publicly readable
                        .requestMatchers(
                                org.springframework.http.HttpMethod.GET,
                                "/api/providers/**")
                        .permitAll()

                        // Product modifications require provider authentication
                        .requestMatchers(
                                org.springframework.http.HttpMethod.POST,
                                "/api/products/provider/*")
                        .hasRole("PROVIDER")

                        .requestMatchers(
                                org.springframework.http.HttpMethod.PUT,
                                "/api/products/*")
                        .hasRole("PROVIDER")

                        .requestMatchers(
                                org.springframework.http.HttpMethod.DELETE,
                                "/api/products/*")
                        .hasRole("PROVIDER")

                        // Product information remains publicly readable
                        .requestMatchers(
                                org.springframework.http.HttpMethod.GET,
                                "/api/products/**")
                        .permitAll()

                        // Other endpoints require authentication
                        .anyRequest()
                        .authenticated())

                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(
            PasswordEncoder passwordEncoder,
            @Value("${APP_ADMIN_USERNAME}") String adminUsername,
            @Value("${APP_ADMIN_PASSWORD}") String adminPassword,
            @Value("${APP_USER_USERNAME}") String userUsername,
            @Value("${APP_USER_PASSWORD}") String userPassword,
            @Value("${APP_PROVIDER_USERNAME}") String providerUsername,
            @Value("${APP_PROVIDER_PASSWORD}") String providerPassword) {

        UserDetails admin = User.withUsername(adminUsername)
                .password(passwordEncoder.encode(adminPassword))
                .roles("ADMIN")
                .build();

        UserDetails regularUser = User.withUsername(userUsername)
                .password(passwordEncoder.encode(userPassword))
                .roles("USER")
                .build();

        UserDetails provider = User.withUsername(providerUsername)
                .password(passwordEncoder.encode(providerPassword))
                .roles("PROVIDER")
                .build();

        return new InMemoryUserDetailsManager(
                admin,
                regularUser,
                provider);
    }
}
