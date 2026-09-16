# Software Requirements Specification

**Project Name:** Fit District  
**Team:** Cameron Wilkerson and RJ Hester  
**Course:** CSC 340  
**Version:** 1.0  
**Date:** 2026-09-16  

---

## 1. Overview

**Vision.** Fit District is designed for online clothing shoppers who want an easier way to discover clothing from multiple retail providers. The platform brings clothing offerings from different providers together in one place so customers can discover, compare, and save while shopping.

**Glossary**
- **Customer:** A user who browses clothing and interacts with clothing providers.
- **Provider:** A retail store or seller that offers clothing through Fit District.
- **Service:** A clothing offering or collection made available by a provider.
- **Subscription:** A connection that allows a customer to subscribe to an available service.
- **Review:** Feedback and a rating submitted by a customer about their shopping experience.

**Primary Users / Roles**
- **Customer (Online Shopper):** Browses clothing, subscribes to available services, and writes reviews for subscribed services.
- **Provider (Retail Store):** Creates and manages clothing offerings, views customer statistics, and responds to reviews.

**Scope (this semester).**
- Customer and provider account/profile management.
- Providers can create and manage clothing offerings.
- Customers can browse clothing from multiple providers.
- Customers can search and filter available clothing.
- Customers can subscribe to available services.
- Customers can write reviews for subscribed services.
- Providers can view customer statistics.
- Providers can reply to customer reviews.
- Application data will be stored persistently.

**Out of scope (deferred).**
- Direct payment processing and checkout.
- Shipping, returns, and refunds.
- Real-time integration with every online clothing retailer.
- Large-scale web scraping of external retailer websites.
- AI clothing recommendations.
- Virtual clothing try-on.
- Native iOS and Android applications.

---

## 2. Functional Requirements (User Stories)

### 2.1 Customer Stories
---
- **US-1 — Manage Customer Profile**  
  _Story:_ As a customer, I want to create and modify my customer profile so that I can keep my account information accurate.  
  _Acceptance:_
  ```gherkin
  Scenario: Create a customer profile
    Given the customer has created an account
    When the customer enters valid profile information
    Then the system saves the customer profile

  Scenario: Modify a customer profile
    Given the customer is logged in and has an existing profile
    When the customer updates valid profile information
    Then the system saves and displays the updated customer profile
  ```

  - **US-2 — View Available Clothing Offerings**  
  _Story:_ As a customer, I want to view available clothing offerings so that I can discover clothing from different providers.  
  _Acceptance:_
  ```gherkin
  Scenario: View available clothing offerings
    Given the customer is logged into their account
    When the customer opens the available clothing offerings section
    Then the system displays the available clothing offerings from providers
  ```

  - **US-3 — Subscribe to Available Services**  
  _Story:_ As a customer, I want to subscribe to available services so that I can follow clothing offerings that interest me.  
  _Acceptance:_
  ```gherkin
  Scenario: Subscribe to an available service
    Given the customer is logged into their account
    And an available service exists
    When the customer chooses to subscribe to the service
    Then the system saves the subscription
    And the service appears in the customer's subscriptions


- **US-4 — Write Reviews**  
  _Story:_ As a customer, I want to write reviews for services I am subscribed to so that I can share feedback about my experience.  
  _Acceptance:_
  ```gherkin
  Scenario: Write a review
    Given the customer is logged into their account
    And the customer is subscribed to the service
    When the customer submits a valid review
    Then the system saves the review
    And the review is displayed for the service
---

## 3. Non-Functional Requirements
  
  - **Performance:** At least 95% of normal page requests should load within 3 seconds.

- **Reliability:** Customer profiles, provider profiles, subscriptions, clothing offerings, and reviews should remain saved after the application is restarted.

- **Security:** 100% of user passwords must be stored securely and must not be stored as plain text.

- **Usability:** At least 90% of test users should be able to browse clothing and locate a provider without assistance.

---

## 4. Assumptions, Constraints, and Policies

- Fit District will support two primary user roles: Customer and Provider.
- Customers and providers must have an account to access features specific to their role.
- Only providers can create clothing offerings.
- Customers must subscribe to a provider before writing a review.
- Providers can only manage their own profiles and clothing offerings.
- Providers can reply to reviews associated with their store.
- The application will use persistent storage to save user and application data.
- The project will be developed within the requirements and timeframe of the CSC 340 course.