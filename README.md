# Emperica Employee Management System

A modern, professional employee management application built with Spring Boot and Vanilla JavaScript.

## Features

- **Admin Authentication**: Secure login and registration for administrators using Basic Authentication.
- **AI-Powered Summarization**: Get instant insights into employee performance and accomplishments using local Ollama AI (Qwen 2.5).
- **Employee CRUD**: Create, Read, Update, and Delete employee records with performance metrics.
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
- Oracle Database
  
### Configuration

1. Create a `.env` file in the root directory:
   ```env
   ADMIN_USERNAME=admin
   ADMIN_PASSWORD=admin123
   ```
2. **AI Setup**: Ensure [Ollama](https://ollama.com) is installed and running:
   ```bash
   ollama run qwen2.5:0.5b
   ```
3. Configure your database connection in `src/main/resources/application.yaml`.

### Running the Application

1. **Backend**:
   ```bash
   ./mvnw spring-boot:run
   ```
2. **Frontend**:
   Open `emperica-ui/frontend/index.html` in your favorite browser.

## Admin Registration

If the `.env` file is missing or you need a new admin, use the "Register Admin" feature on the login page to create a local account.

## Screenshots

<img width="1197" height="726" alt="image" src="https://github.com/user-attachments/assets/20ea4577-6bb4-410b-a62f-6422889a065c" />

<img width="1193" height="537" alt="image" src="https://github.com/user-attachments/assets/ea8e60b9-32ab-4a95-954a-c7975292e0c3" />

<img width="1188" height="799" alt="image" src="https://github.com/user-attachments/assets/d4766eb9-d786-4224-8819-ab8f710c5dd3" />

---
Developed by [Aditya Shankar](https://github.com/AdityaShankar1)
