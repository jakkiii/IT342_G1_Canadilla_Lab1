# Functional Requirements Specification (FRS)
## User Registration and Authentication System

---

## 1. Introduction

### 1.1 Purpose
This document describes the functional requirements for a User Registration and Authentication System designed for web and mobile applications.

### 1.2 Scope
The system provides secure user registration, authentication, and profile management capabilities through a RESTful API backend and responsive web/mobile interfaces.

### 1.3 System Overview
- **Backend**: Spring Boot REST API with MySQL database
- **Web Application**: ReactJS single-page application
- **Mobile Application**: (To be implemented in Lab 2)

---

## 2. System Architecture

### 2.1 Technology Stack

#### Backend
- **Framework**: Spring Boot 3.2.2
- **Language**: Java 17
- **Database**: MySQL 8.0
- **Security**: Spring Security + JWT
- **Password Encryption**: BCrypt

#### Web Application
- **Framework**: React 18.2
- **Router**: React Router DOM 6.22
- **HTTP Client**: Axios 1.6.7

### 2.2 Architecture Pattern
- **Backend**: Layered Architecture (Controller → Service → Repository → Entity)
- **Web**: Component-based architecture with React
- **API**: RESTful API design principles

---

## 3. Functional Requirements

### 3.1 User Registration (FR-001)

**Description**: Allow new users to create an account

**Endpoint**: `POST /api/auth/register`

**Input Fields**:
- Username (required, 3-50 characters, unique)
- Email (required, valid email format, unique)
- Password (required, minimum 6 characters)
- First Name (optional)
- Last Name (optional)
- Phone Number (optional)

**Validation Rules**:
- Username must be unique
- Email must be unique and valid format
- Password must be at least 6 characters
- All required fields must be provided

**Success Response**:
```json
{
  "message": "User registered successfully!"
}
```

**Error Responses**:
- 400: Username already taken
- 400: Email already in use
- 400: Validation errors

---

### 3.2 User Login (FR-002)

**Description**: Authenticate existing users and provide access token

**Endpoint**: `POST /api/auth/login`

**Input Fields**:
- Username or Email (required)
- Password (required)

**Success Response**:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "id": 1,
  "username": "john_doe",
  "email": "john@example.com"
}
```

**Error Responses**:
- 401: Invalid credentials

**Token Details**:
- Algorithm: HS256
- Expiration: 24 hours (86400000 ms)
- Stored in localStorage on client

---

### 3.3 Get Current User (FR-003)

**Description**: Retrieve authenticated user's profile information

**Endpoint**: `GET /api/user/me`

**Authentication**: Required (Bearer Token)

**Success Response**:
```json
{
  "id": 1,
  "username": "john_doe",
  "email": "john@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "phoneNumber": "+1234567890",
  "createdAt": "2026-02-07T10:30:00",
  "updatedAt": "2026-02-07T10:30:00"
}
```

**Error Responses**:
- 401: Unauthorized (invalid or expired token)

---

### 3.4 User Logout (FR-004)

**Description**: End user session and clear authentication data

**Implementation**: Client-side operation
- Remove token from localStorage
- Remove user data from localStorage
- Redirect to login page

---

## 4. Database Schema

### 4.1 Users Table

| Column | Type | Constraints |
|--------|------|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| username | VARCHAR(50) | NOT NULL, UNIQUE |
| email | VARCHAR(100) | NOT NULL, UNIQUE |
| password | VARCHAR(255) | NOT NULL (BCrypt hashed) |
| first_name | VARCHAR(50) | NULL |
| last_name | VARCHAR(50) | NULL |
| phone_number | VARCHAR(20) | NULL |
| created_at | TIMESTAMP | NOT NULL, DEFAULT CURRENT_TIMESTAMP |
| updated_at | TIMESTAMP | NOT NULL, DEFAULT CURRENT_TIMESTAMP ON UPDATE |
| active | BOOLEAN | NOT NULL, DEFAULT TRUE |

---

## 5. Security Requirements

### 5.1 Password Security
- All passwords encrypted using BCrypt algorithm
- Minimum password length: 6 characters
- Passwords never stored in plain text
- Passwords never returned in API responses

### 5.2 API Security
- JWT-based authentication
- Token required for protected endpoints
- CORS enabled for web application origin
- Session management: Stateless (JWT)

### 5.3 Data Validation
- Input validation on both client and server
- SQL injection protection via JPA
- XSS protection via proper encoding

---

## 6. User Interface Requirements

### 6.1 Register Page

**Features**:
- Form with all required and optional fields
- Real-time validation feedback
- Error message display
- Success message with redirect to login
- Link to login page for existing users

**Fields**:
- Username (required)
- Email (required)
- Password (required)
- First Name (optional)
- Last Name (optional)
- Phone Number (optional)

**Screenshot**: *(To be added after implementation)*

---

### 6.2 Login Page

**Features**:
- Username/Email input field
- Password input field
- Login button
- Error message display
- Link to registration page
- Redirect to dashboard on success

**Screenshot**: *(To be added after implementation)*

---

### 6.3 Dashboard/Profile Page

**Features**:
- Display user information:
  - User ID
  - Username
  - Email
  - First Name (if provided)
  - Last Name (if provided)
  - Phone Number (if provided)
  - Member Since date
  - Last Updated date
- Logout button
- Protected route (requires authentication)

**Screenshot**: *(To be added after implementation)*

---

### 6.4 Logout Functionality

**Features**:
- Logout button in dashboard header
- Clear authentication data
- Redirect to login page
- Prevent access to protected pages after logout

**Screenshot**: *(To be added after implementation)*

---

## 7. API Endpoints Summary

| Method | Endpoint | Auth Required | Description |
|--------|----------|---------------|-------------|
| POST | /api/auth/register | No | Register new user |
| POST | /api/auth/login | No | Authenticate user |
| GET | /api/user/me | Yes | Get current user profile |

---

## 8. Non-Functional Requirements

### 8.1 Performance
- API response time: < 500ms for authentication operations
- Database queries optimized with proper indexing

### 8.2 Scalability
- Stateless authentication allows horizontal scaling
- Database connection pooling configured

### 8.3 Reliability
- Proper error handling and logging
- Transaction management for data integrity

### 8.4 Usability
- Intuitive user interface
- Clear error messages
- Responsive design

---

## 9. Future Enhancements (Out of Scope for Lab 1)

- Mobile application (Lab 2)
- Password reset functionality
- Email verification
- User roles and permissions
- Profile picture upload
- Two-factor authentication
- Social media login integration

---

## 10. Appendices

### 10.1 Entity Relationship Diagram
See: `ERD_Diagram.md`

### 10.2 UML Diagrams
See: 
- `UML_Class_Diagram.md`
- `UML_Sequence_Diagrams.md`

### 10.3 Screenshots
See: `/docs/screenshots/` folder

---

**Document Version**: 1.0  
**Last Updated**: February 7, 2026  
**Author**: G5 - Canadilla
