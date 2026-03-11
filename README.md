# Emperica Employee Management System

A modern, professional employee management application built with Spring Boot and Vanilla JavaScript.

## Features

- **Admin Authentication**: Secure login and registration for administrators using Basic Authentication.
- **Employee CRUD**: Create, Read, Update, and Delete employee records.
- **Department Management**: Categorize employees by departments.
- **Modern UI**: A sleek, dark-mode dashboard designed with Tailwind CSS for a premium feel.
- **Environment Support**: Configuration via `.env` files for sensitive credentials.

## Tech Stack

- **Backend**: Spring Boot, Spring Data JPA, Spring Security, Oracle Database.
- **Frontend**: Vanilla JavaScript, Tailwind CSS, Google Fonts (Inter).
- **Environment**: Dotenv for Java.

## Getting Started

### Prerequisites

- JDK 17 or higher
- Maven
- Oracle Database (or compatible)

### Configuration

1. Create a `.env` file in the root directory:
   ```env
   ADMIN_USERNAME=admin
   ADMIN_PASSWORD=admin123
   ```
2. Configure your database connection in `src/main/resources/application.properties` (or as per your existing setup).

### Running the Application

1. **Backend**:
   ```bash
   ./mvnw spring-boot:run
   ```
2. **Frontend**:
   Open `emperica-ui/frontend/index.html` in your favorite browser.

## Admin Registration

If the `.env` file is missing or you need a new admin, use the "Register Admin" feature on the login page to create a local account.

---
Developed by [Aditya Shankar](https://github.com/AdityaShankar1)
