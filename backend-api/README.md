# Fit District — Backend API

## Project Overview

Fit District is a clothing marketplace application where customers can discover products from multiple providers, manage their profiles, and subscribe to clothing providers. The backend is built using Java 21, Spring Boot, Spring Data JPA, and PostgreSQL.

The application uses Neon PostgreSQL for data persistence and is deployed to Render using Docker.

## Live Render Deployment

**Live API URL:** https://fit-district-api.onrender.com

The API is deployed using Docker on Render.

**Cold Start Note:** Render's free instance may spin down after inactivity. The first API request may take a minute or longer to respond.

This application is a REST API, so the root URL may return 404. Use the documented API endpoints.

## Customer and Subscription API

**Implemented by:** Cameron Wilkerson

| HTTP Method | Endpoint | Description / Use Case | Success Status | Implemented By |
|---|---|---|---|---|
| POST | `/api/customers` | Create customer profile | 201 Created | Cameron Wilkerson |
| GET | `/api/customers/{id}` | View customer profile | 200 OK | Cameron Wilkerson |
| PUT | `/api/customers/{id}` | Modify customer profile | 200 OK | Cameron Wilkerson |
| DELETE | `/api/customers/{id}` | Delete customer profile | 204 No Content | Cameron Wilkerson |
| POST | `/api/customers/{customerId}/subscriptions` | Subscribe to a clothing provider | 201 Created | Cameron Wilkerson |
| GET | `/api/customers/{customerId}/subscriptions` | View customer subscriptions | 200 OK | Cameron Wilkerson |
| DELETE | `/api/subscriptions/{id}` | Delete customer subscription | 204 No Content | Cameron Wilkerson |

### Example Request: Create Customer

**POST** `/api/customers`

```json
{
  "fullName": "Test Customer",
  "email": "testcustomer@example.com",
  "phoneNumber": "3365550123",
  "stylePreference": "Streetwear"
}
```

**Expected success status:** 201 Created

### Example Request: Update Customer

**PUT** `/api/customers/1`

```json
{
  "fullName": "Updated Customer",
  "email": "updatedcustomer@example.com",
  "phoneNumber": "3365550123",
  "stylePreference": "Casual"
}
```

**Expected success status:** 200 OK

### Example Request: Subscribe to Provider

**POST** `/api/customers/1/subscriptions`

```json
{
  "providerId": 3
}
```

**Expected success status:** 201 Created

## Customer API Error Handling

| HTTP Status | Meaning |
|---|---|
| 200 OK | Resource successfully retrieved or updated |
| 201 Created | Customer or subscription created |
| 204 No Content | Resource deleted |
| 400 Bad Request | Invalid customer input |
| 401 Unauthorized | Missing or incorrect authentication |
| 404 Not Found | Requested customer does not exist |
| 409 Conflict | Duplicate subscription attempt |

These HTTP responses were tested using EchoAPI against the deployed Render application.

## Authentication

The API uses HTTP Basic Authentication for protected endpoints. Authentication credentials are stored privately in Render environment variables and are not included in this repository.

## Provider and System Administrator APIs

*To be completed by the team member responsible for provider and system administrator use cases.*

## UML Class Diagram

*The finalized team UML diagram, including entity relationships and multiplicities, will be added here.*

## GitHub Issues and User Stories

*The team will add links to the GitHub issues associated with the implemented use cases.*
