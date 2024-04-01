# Simple Customer Management System

This Spring Boot application demonstrates a basic Customer Management System using Spring Data JPA for handling CRUD operations on customer data stored in a database. It provides a REST API for managing customer information.

## Features

- **Spring Boot Framework**: Utilizes Spring Boot for rapid development and effortless configuration.
- **Spring Data JPA**: Leverages Spring Data JPA for easy database interactions and CRUD operations.
- **RESTful API**: Offers a RESTful API for creating, retrieving, updating, and deleting customer information.
- **Docker Support**: Includes a Docker Compose file for containerization and easy deployment.

## Project Structure

- `Customer.java`: Entity class representing the customer with fields like `id`, `name`, `email`, and `age`.
- `CustomerRepository.java`: Interface extending `JpaRepository` for repository-layer operations on the `Customer` entity.
- `Main.java`: Main application class and REST controller handling API endpoints for customer operations.
- `application.yml`: Spring Boot application configuration file.
- `docker-compose.yml`: Docker Compose configuration for deploying the application and its dependencies.
- `pom.xml`: Maven build configuration file detailing the project's dependencies.
