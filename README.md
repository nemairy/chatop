# Chatop

Chatop is a Spring Boot-based REST API for managing rental listings, user authentication, and file uploads. It uses MySQL for data persistence and supports JWT-based security and Swagger/OpenAPI documentation.

## Features
- User registration and authentication (JWT)
- CRUD operations for rental listings
- File upload support (images)
- API documentation with Swagger UI
- Global exception handling

## Technologies
- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA (Hibernate)
- MySQL
- HikariCP
- Swagger/OpenAPI
- Jackson (JSON serialization)

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven
- MySQL database

### Configuration
Set the following environment variables or update `src/main/resources/application.properties`:
- `DB_URL` - MySQL JDBC URL
- `DB_USERNAME` - MySQL username
- `DB_PASSWORD` - MySQL password
- `JWT_KEY` - Secret key for JWT

### Build & Run
```bash
mvn clean install
mvn spring-boot:run
```

The API will be available at `http://localhost:9006`.

### API Documentation
Swagger UI: [http://localhost:9006/swagger-ui.html](http://localhost:9006/swagger-ui.html)

## File Uploads
Uploaded files are stored in the `uploads/` directory. Max file size: 10MB.

## Author
Abubaker Nemairy

## License
This project is for educational purposes (OpenClassrooms).