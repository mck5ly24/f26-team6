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
5. Milestones (course‑aligned)
M1 Requirements — this file + stories opened as issues.
M2 High‑fidelity prototype — core customer/provider flows fully interactive.
M3 Design — architecture, schema, API outline.
M4 Backend API — key endpoints + tests.
M5 Increment — ≥2 use cases end‑to‑end.
M6 Final — complete system & documentation.
6. Change Management
Stories are living artifacts; changes are tracked via repository issues and linked pull requests.
Major changes should update this SRS.