# Library Management System

A RESTful API built with Spring Boot for managing library operations including book inventory, member management, and book borrowing/returning functionality.

## Features

- **Book Management**: Add and view books with details like title, author, publisher, type, and quantity
- **Member Management**: Register students and faculty members with role-specific borrowing limits
- **Borrowing System**: Borrow and return books with automated inventory tracking
- **Member Types**:
  - Students: 10 books borrowing limit, requires roll number
  - Faculty: 5 books borrowing limit, requires department
- **Swagger API Documentation**: Interactive API documentation at `/swagger-ui.html`

## Technology Stack

- **Framework**: Spring Boot 4.0.6
- **Language**: Java 21
- **Database**: H2 (in-memory)
- **ORM**: Hibernate/JPA
- **API Documentation**: SpringDoc OpenAPI (Swagger)
- **Build Tool**: Maven
- **Mapping**: ModelMapper
- **Validation**: Jakarta Bean Validation

## Architecture

The application follows a clean layered architecture with SOLID principles:

```
├── controller/       # REST endpoints
├── service/          # Business logic interfaces
│   └── serviceImpl/  # Service implementations
├── repository/       # Data access layer
├── entity/           # JPA entities
├── dto/              # Data transfer objects
├── exception/        # Custom exceptions
├── config/           # Configuration classes
└── enums/            # Enumerations
```

## API Endpoints

### Books

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/books` | Create a new book |
| GET | `/api/books` | Get all books |

### Members

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/members/student` | Create a student member |
| POST | `/api/members/faculty` | Create a faculty member |
| GET | `/api/members` | Get all members |
| GET | `/api/members/{id}/borrowed-books` | Get books borrowed by a member |

### Borrowing

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/members/{memberId}/borrow/{bookId}` | Borrow a book |
| POST | `/api/members/{memberId}/return/{bookId}` | Return a book |

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6+

### Installation

1. Clone the repository
```bash
git clone <repository-url>
cd LibraryManagement
```

2. Build the project
```bash
./mvnw clean install
```

3. Run the application
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

### Access Swagger UI

Open your browser and navigate to:
```
http://localhost:8080/swagger-ui.html
```

### Access H2 Console

```
http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:librarydb
Username: sa
Password: password
```

## API Usage Examples

### Create a Book

```bash
POST http://localhost:8080/api/books
Content-Type: application/json

{
  "title": "Clean Code",
  "author": "Robert Martin",
  "publisher": "Prentice Hall",
  "bookType": "TEXTBOOK",
  "quantity": 5
}
```

**Valid Book Types**: `FICTION`, `NON_FICTION`, `BIOGRAPHY`, `TEXTBOOK`, `JOURNAL`, `REFERENCE`

### Create a Student Member

```bash
POST http://localhost:8080/api/members/student
Content-Type: application/json

{
  "name": "John Doe",
  "rollNumber": "CS2021001"
}
```

### Create a Faculty Member

```bash
POST http://localhost:8080/api/members/faculty
Content-Type: application/json

{
  "name": "Dr. Smith",
  "department": "Computer Science"
}
```

### Borrow a Book

```bash
POST http://localhost:8080/api/members/1/borrow/1
```

### Return a Book

```bash
POST http://localhost:8080/api/members/1/return/1
```

### Get Borrowed Books by Member

```bash
GET http://localhost:8080/api/members/1/borrowed-books
```

## Business Rules

1. **Book Availability**: Books can only be borrowed if quantity > 0
2. **Borrowing Limits**:
   - Students can borrow up to 10 books
   - Faculty can borrow up to 5 books
3. **Return Validation**: Members can only return books they have borrowed
4. **Automatic Inventory**: Book quantity is automatically updated on borrow/return

## Project Structure Highlights

### SOLID Principles Implementation

- **Single Responsibility**: Separate services for Book, Member, and Borrowing operations
- **Open/Closed**: Member hierarchy allows easy extension for new member types
- **Liskov Substitution**: StudentMember and FacultyMember are proper substitutes for Member
- **Interface Segregation**: Focused service interfaces (IBookService, IMemberService, IBorrowingService)
- **Dependency Inversion**: Controllers depend on service interfaces, not implementations

### Design Patterns

- **DTO Pattern**: Separate request/response objects from entities
- **Repository Pattern**: Spring Data JPA repositories for data access
- **Service Layer Pattern**: Business logic encapsulated in service layer
- **Builder Pattern**: Lombok builders for entity and DTO construction

## Configuration

Key configuration in `application.properties`:

```properties
# Database
spring.datasource.url=jdbc:h2:mem:librarydb
spring.jpa.hibernate.ddl-auto=update

# Swagger
springdoc.swagger-ui.path=/swagger-ui.html

# Logging
logging.level.com.akshayHole.LibraryManagement=INFO
```

## Error Handling

The application includes global exception handling for:

- **ResourceNotFoundException**: 404 - When book or member not found
- **BorrowingException**: 409 - When business rules are violated
- **MethodArgumentNotValidException**: 400 - When validation fails
- **Generic Exception**: 500 - For unexpected errors

## Future Enhancements

- Add due dates and late fee calculation
- Implement user authentication and authorization
- Add book reservation functionality
- Create borrowing history and analytics
- Add pagination for list endpoints
- Implement search and filter capabilities

## License

This project is for educational purposes.

## Author

Akshay Hole
