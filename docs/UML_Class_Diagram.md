# UML Class Diagram
## User Authentication System

---

## Class Diagram

```mermaid
classDiagram
    class User {
        -Long id
        -String username
        -String email
        -String password
        -String firstName
        -String lastName
        -String phoneNumber
        -LocalDateTime createdAt
        -LocalDateTime updatedAt
        -Boolean active
        +getId() Long
        +setId(Long) void
        +getUsername() String
        +setUsername(String) void
        +getEmail() String
        +setEmail(String) void
    }

    class RegisterRequest {
        -String username
        -String email
        -String password
        -String firstName
        -String lastName
        -String phoneNumber
        +validate() boolean
    }

    class LoginRequest {
        -String usernameOrEmail
        -String password
        +validate() boolean
    }

    class AuthResponse {
        -String token
        -String type
        -Long id
        -String username
        -String email
        +getToken() String
    }

    class UserResponse {
        -Long id
        -String username
        -String email
        -String firstName
        -String lastName
        -String phoneNumber
        -LocalDateTime createdAt
        -LocalDateTime updatedAt
    }

    class MessageResponse {
        -String message
        +getMessage() String
    }

    class UserRepository {
        <<interface>>
        +findByUsername(String) Optional~User~
        +findByEmail(String) Optional~User~
        +findByUsernameOrEmail(String, String) Optional~User~
        +existsByUsername(String) Boolean
        +existsByEmail(String) Boolean
    }

    class AuthService {
        -UserRepository userRepository
        -PasswordEncoder passwordEncoder
        -AuthenticationManager authManager
        -JwtTokenProvider tokenProvider
        +register(RegisterRequest) MessageResponse
        +login(LoginRequest) AuthResponse
    }

    class UserService {
        -UserRepository userRepository
        +getCurrentUser(String) UserResponse
        -convertToUserResponse(User) UserResponse
    }

    class AuthController {
        -AuthService authService
        +register(RegisterRequest) ResponseEntity
        +login(LoginRequest) ResponseEntity
    }

    class UserController {
        -UserService userService
        +getCurrentUser() ResponseEntity
    }

    class JwtTokenProvider {
        -String jwtSecret
        -long jwtExpiration
        +generateToken(Authentication) String
        +generateTokenFromUsername(String) String
        +getUsernameFromToken(String) String
        +validateToken(String) boolean
    }

    class JwtAuthenticationFilter {
        -JwtTokenProvider tokenProvider
        -UserDetailsService userDetailsService
        +doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain) void
        -getJwtFromRequest(HttpServletRequest) String
    }

    class CustomUserDetailsService {
        -UserRepository userRepository
        +loadUserByUsername(String) UserDetails
    }

    class SecurityConfig {
        -UserDetailsService userDetailsService
        -JwtAuthenticationFilter jwtAuthFilter
        +passwordEncoder() PasswordEncoder
        +authenticationProvider() DaoAuthenticationProvider
        +authenticationManager(AuthConfig) AuthenticationManager
        +filterChain(HttpSecurity) SecurityFilterChain
        +corsConfigurationSource() CorsConfigurationSource
    }

    %% Relationships
    AuthController --> AuthService : uses
    UserController --> UserService : uses
    AuthService --> UserRepository : uses
    UserService --> UserRepository : uses
    AuthService --> JwtTokenProvider : uses
    JwtAuthenticationFilter --> JwtTokenProvider : uses
    JwtAuthenticationFilter --> CustomUserDetailsService : uses
    CustomUserDetailsService --> UserRepository : uses
    SecurityConfig --> CustomUserDetailsService : configures
    SecurityConfig --> JwtAuthenticationFilter : configures
    
    UserRepository --> User : manages
    AuthService --> RegisterRequest : receives
    AuthService --> LoginRequest : receives
    AuthService --> AuthResponse : returns
    AuthService --> MessageResponse : returns
    UserService --> UserResponse : returns
    UserService --> User : converts
```

---

## Package Structure

```
com.canadilla.userauth
├── entity
│   └── User
├── dto
│   ├── RegisterRequest
│   ├── LoginRequest
│   ├── AuthResponse
│   ├── UserResponse
│   └── MessageResponse
├── repository
│   └── UserRepository
├── service
│   ├── AuthService
│   └── UserService
├── controller
│   ├── AuthController
│   └── UserController
├── security
│   ├── JwtTokenProvider
│   ├── JwtAuthenticationFilter
│   ├── CustomUserDetailsService
│   └── SecurityConfig
└── UserAuthApplication
```

---

## Class Descriptions

### Entity Layer

**User**: JPA entity representing the users table in the database. Contains user account information and authentication credentials.

### DTO Layer

**RegisterRequest**: Data Transfer Object for user registration requests. Contains validation annotations.

**LoginRequest**: DTO for login requests with username/email and password.

**AuthResponse**: DTO for successful authentication responses, includes JWT token and user info.

**UserResponse**: DTO for user profile information (excludes sensitive data like password).

**MessageResponse**: Generic DTO for simple success/error messages.

### Repository Layer

**UserRepository**: Spring Data JPA repository interface for User entity. Provides database operations.

### Service Layer

**AuthService**: Business logic for user registration and authentication. Handles password encryption and JWT generation.

**UserService**: Business logic for user profile operations. Retrieves and formats user information.

### Controller Layer

**AuthController**: REST API endpoints for authentication operations (/api/auth/*).

**UserController**: REST API endpoints for user operations (/api/user/*).

### Security Layer

**JwtTokenProvider**: Utility class for JWT token generation and validation.

**JwtAuthenticationFilter**: Spring Security filter for JWT-based authentication.

**CustomUserDetailsService**: Implementation of UserDetailsService for loading user details.

**SecurityConfig**: Spring Security configuration class. Defines security rules and beans.

---

## Design Patterns Used

1. **Layered Architecture**: Separation of concerns across layers (Controller → Service → Repository → Entity)
2. **Repository Pattern**: Data access abstraction through UserRepository
3. **DTO Pattern**: Data transfer between layers using dedicated DTO classes
4. **Dependency Injection**: Spring's @Autowired for loose coupling
5. **Filter Chain Pattern**: JWT authentication filter in security chain
6. **Factory Pattern**: Spring Security's authentication provider factory

---

**Document Version**: 1.0  
**Last Updated**: February 7, 2026  
**Author**: G5 - Canadilla
