Requirements – Starter Template
Project Name: Your App Name
Team: Names and roles
Course: CSC 340
Version: 1.0
Date: 2026-08-25

1. Overview
Vision. One or two sentences: who this is for, the core problem, and the outcome.

Glossary Terms used in the project

Term 1: description.
Term 2: description
Primary Users / Roles.

Customer (e.g., Student/Patient/Pet Owner/etc. ) — 1 line goal statement.
Provider (e.g., Teacher/Doctor/Pet Sitter/etc. ) — 1 line goal statement.
SysAdmin (optional) — 1 line goal statement.
Scope (this semester).

<capability 1>
<capability 2>
<capability 3>
Out of scope (deferred).

<deferred 1>
<deferred 2>
This document is requirements‑level and solution‑neutral; design decisions (UI layouts, API endpoints, schemas) are documented separately.

2. Functional Requirements (User Stories)
Write each story as: As a <role>, I want <capability>, so that <benefit>. Each story includes at least one Given/When/Then scenario.

2.1 Customer Stories
US‑1 —
Story: As a customer, I want … so that …
Acceptance:

Scenario: <happy path>
  Given <preconditions>
  When  <action>
  Then  <observable outcome>
US‑2 —
Story: As a customer, I want … so that …
Acceptance:

Scenario: <happy path>
  Given <preconditions>
  When  <action>
  Then  <observable outcome>
2.2 Provider Stories
- **US-20 — Manage Provider Profile**  
  _Story:_ As a provider, I want to create, modify, or remove my provider profile, so that I can keep my business information accurate and up to date.  
  _Acceptance:_
  ```gherkin
  Scenario: Provider updates their profile
    Given the provider is logged into their account
    When the provider updates their profile or business information
    Then the updated information should be saved and displayed on their provider profile
  ```

- **US-21 — Create Products**  
  _Story:_ As a provider, I want to create products, so that customers can view the clothing items I have available.  
  _Acceptance:_
  ```gherkin
  Scenario: Provider adds a new product
    Given the provider is logged into their account
    When the provider enters the product name, description, image, price, size, and other relevant information
    Then the new product should be added and made available for customers to view
  ```

- **US-22 — Manage Products**  
  _Story:_ As a provider, I want to update or remove my products, so that customers receive accurate information about my available products.  
  _Acceptance:_
  ```gherkin
  Scenario: Provider manages an existing product
    Given the provider has an existing product
    When the provider updates its price, availability, size, or other product details
    Then the changes should be saved and displayed to customers
  ```

- **US-23 — View Customer Activity**  
  _Story:_ As a provider, I want to view customer activity related to my products, so that I can understand customer interest and feedback.  
  _Acceptance:_
  ```gherkin
  Scenario: Provider views customer activity
    Given customers have interacted with the provider's products
    When the provider views the customer activity for their products
    Then the provider should be able to see views, favorites, and reviews related to those products
  ```

- **US-24 — Reply to Reviews**  
  _Story:_ As a provider, I want to reply to customer reviews, so that I can respond to customer feedback about my products.  
  _Acceptance:_
  ```gherkin
  Scenario: Provider replies to a review
    Given a customer has left a review on a provider's product
    When the provider submits a response to the review
    Then the response should be displayed with the customer's review
  ```
2.3 SysAdmin Stories
- **US-30 — Manage User Access**  
  _Story:_ As a sysadmin, I want to manage customer and provider accounts, so that I can maintain proper access and enforce platform rules.  
  _Acceptance:_
  ```gherkin
  Scenario: Admin manages a user account
    Given the sysadmin is logged into an admin account
    When the sysadmin updates permissions, suspends an account, or removes an account
    Then the user's account access should be updated accordingly
  ```

- **US-31 — Moderate Products**  
  _Story:_ As a sysadmin, I want to review and manage product listings, so that products follow Fit District's guidelines.  
  _Acceptance:_
  ```gherkin
  Scenario: Admin moderates a product listing
    Given a product listing exists on Fit District
    When the sysadmin reviews, approves, edits, or removes the product listing
    Then the product listing should reflect the sysadmin's action and comply with Fit District's guidelines
  ```

- **US-32 — Moderate Reviews**  
  _Story:_ As a sysadmin, I want to moderate customer reviews and reports, so that inappropriate or fraudulent content can be handled.  
  _Acceptance:_
  ```gherkin
  Scenario: Admin moderates a customer review
    Given a customer review or report has been submitted
    When the sysadmin reviews the content and determines that action is needed
    Then the sysadmin should be able to remove the review or handle the submitted report
  ```

- **US-33 — View Usage Statistics**  
  _Story:_ As a sysadmin, I want to view platform usage statistics, so that I can monitor activity across Fit District.  
  _Acceptance:_
  ```gherkin
  Scenario: Admin views platform statistics
    Given the platform has recorded user and product activity
    When the sysadmin opens the platform statistics
    Then the sysadmin should be able to view popular products, trending brands, customer activity, provider activity, sales activity, and overall site usage
  ```
3. Non‑Functional Requirements (make them measurable)
Performance: description
Availability/Reliability: description
Security/Privacy: description
Usability: description
4. Assumptions, Constraints, and Policies
list any rules, policies, assumptions, etc.
## 5. Milestones (course-aligned)

- **M1 Requirements** - Complete the Fit District SRS document with Customer, Provider, and SysAdmin user stories and acceptance criteria. Track the requirements through GitHub issues.

- **M2 High-fidelity prototype** - Create an interactive Fit District prototype that demonstrates the main customer and provider flows.

- **M3 Design** - Develop the architecture, database schema, and API outline for the Fit District system.

- **M4 Backend API** - Implement and test the key backend API endpoints needed for Fit District.

- **M5 Increment** - Complete at least two Fit District use cases end-to-end.

- **M6 Final** - Complete the Fit District system, testing, and project documentation.
## 6. Change Management

Changes to Fit District requirements will be tracked through GitHub issues and linked pull requests. Team members will create or update issues when requirements or user stories need to be changed.

Major changes to the system requirements, user stories, or acceptance criteria should also be reflected in this SRS document to keep the project documentation up to date.
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
