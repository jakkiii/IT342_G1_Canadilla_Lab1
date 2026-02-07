# UML Sequence Diagrams
## User Authentication System

---

## 1. User Registration Flow

```mermaid
sequenceDiagram
    actor User
    participant WebUI as Web UI
    participant AuthController
    participant AuthService
    participant UserRepository
    participant Database
    participant PasswordEncoder

    User->>WebUI: Enter registration details
    WebUI->>WebUI: Validate input (client-side)
    WebUI->>AuthController: POST /api/auth/register
    
    AuthController->>AuthController: Validate @RequestBody
    AuthController->>AuthService: register(RegisterRequest)
    
    AuthService->>UserRepository: existsByUsername(username)
    UserRepository->>Database: SELECT username
    Database-->>UserRepository: Result
    UserRepository-->>AuthService: false
    
    AuthService->>UserRepository: existsByEmail(email)
    UserRepository->>Database: SELECT email
    Database-->>UserRepository: Result
    UserRepository-->>AuthService: false
    
    AuthService->>PasswordEncoder: encode(password)
    PasswordEncoder-->>AuthService: hashedPassword
    
    AuthService->>AuthService: Create User entity
    AuthService->>UserRepository: save(user)
    UserRepository->>Database: INSERT INTO users
    Database-->>UserRepository: User saved
    UserRepository-->>AuthService: User
    
    AuthService-->>AuthController: MessageResponse("User registered successfully!")
    AuthController-->>WebUI: 201 Created
    WebUI-->>User: Show success message
    WebUI->>WebUI: Redirect to login page
```

---

## 2. User Login Flow

```mermaid
sequenceDiagram
    actor User
    participant WebUI as Web UI
    participant AuthController
    participant AuthService
    participant AuthManager as AuthenticationManager
    participant UserDetailsService
    participant UserRepository
    participant Database
    participant JwtTokenProvider
    participant PasswordEncoder

    User->>WebUI: Enter username/email and password
    WebUI->>WebUI: Validate input (client-side)
    WebUI->>AuthController: POST /api/auth/login
    
    AuthController->>AuthController: Validate @RequestBody
    AuthController->>AuthService: login(LoginRequest)
    
    AuthService->>AuthManager: authenticate(credentials)
    AuthManager->>UserDetailsService: loadUserByUsername(usernameOrEmail)
    
    UserDetailsService->>UserRepository: findByUsernameOrEmail(value, value)
    UserRepository->>Database: SELECT * FROM users WHERE username=? OR email=?
    Database-->>UserRepository: User record
    UserRepository-->>UserDetailsService: User
    
    UserDetailsService-->>AuthManager: UserDetails
    AuthManager->>PasswordEncoder: matches(rawPassword, encodedPassword)
    PasswordEncoder-->>AuthManager: true
    AuthManager-->>AuthService: Authentication object
    
    AuthService->>JwtTokenProvider: generateToken(authentication)
    JwtTokenProvider->>JwtTokenProvider: Create JWT with username
    JwtTokenProvider-->>AuthService: JWT token
    
    AuthService->>UserRepository: findByUsernameOrEmail(value, value)
    UserRepository->>Database: SELECT * FROM users
    Database-->>UserRepository: User
    UserRepository-->>AuthService: User
    
    AuthService->>AuthService: Create AuthResponse
    AuthService-->>AuthController: AuthResponse(token, id, username, email)
    AuthController-->>WebUI: 200 OK with token
    
    WebUI->>WebUI: Store token in localStorage
    WebUI-->>User: Show success
    WebUI->>WebUI: Redirect to dashboard
```

---

## 3. Get Current User Profile Flow

```mermaid
sequenceDiagram
    actor User
    participant WebUI as Web UI
    participant JwtAuthFilter as JwtAuthenticationFilter
    participant JwtTokenProvider
    participant UserController
    participant UserService
    participant UserRepository
    participant Database
    participant SecurityContext

    User->>WebUI: Access dashboard
    WebUI->>WebUI: Get token from localStorage
    WebUI->>UserController: GET /api/user/me<br/>Header: Authorization: Bearer {token}
    
    UserController->>JwtAuthFilter: Intercept request
    JwtAuthFilter->>JwtAuthFilter: Extract JWT from header
    JwtAuthFilter->>JwtTokenProvider: validateToken(jwt)
    JwtTokenProvider-->>JwtAuthFilter: true
    
    JwtAuthFilter->>JwtTokenProvider: getUsernameFromToken(jwt)
    JwtTokenProvider-->>JwtAuthFilter: username
    
    JwtAuthFilter->>SecurityContext: Set authentication
    JwtAuthFilter->>UserController: Continue request
    
    UserController->>SecurityContext: Get authentication
    SecurityContext-->>UserController: username
    
    UserController->>UserService: getCurrentUser(username)
    UserService->>UserRepository: findByUsername(username)
    UserRepository->>Database: SELECT * FROM users WHERE username=?
    Database-->>UserRepository: User record
    UserRepository-->>UserService: User
    
    UserService->>UserService: convertToUserResponse(user)
    UserService-->>UserController: UserResponse (without password)
    
    UserController-->>WebUI: 200 OK with UserResponse
    WebUI->>WebUI: Display user information
    WebUI-->>User: Show profile
```

---

## 4. User Logout Flow

```mermaid
sequenceDiagram
    actor User
    participant WebUI as Web UI
    participant LocalStorage

    User->>WebUI: Click logout button
    WebUI->>LocalStorage: removeItem('token')
    LocalStorage-->>WebUI: Token removed
    WebUI->>LocalStorage: removeItem('user')
    LocalStorage-->>WebUI: User data removed
    WebUI->>WebUI: Clear authentication state
    WebUI->>WebUI: Redirect to login page
    WebUI-->>User: Show login page
```

---

## 5. JWT Token Validation Flow

```mermaid
sequenceDiagram
    participant Client
    participant JwtAuthFilter as JwtAuthenticationFilter
    participant JwtTokenProvider
    participant UserDetailsService
    participant SecurityContext
    participant ProtectedEndpoint as Protected Endpoint

    Client->>JwtAuthFilter: Request with Authorization header
    JwtAuthFilter->>JwtAuthFilter: Extract Bearer token
    
    alt Token exists
        JwtAuthFilter->>JwtTokenProvider: validateToken(jwt)
        
        alt Token valid
            JwtTokenProvider->>JwtTokenProvider: Parse and verify signature
            JwtTokenProvider->>JwtTokenProvider: Check expiration
            JwtTokenProvider-->>JwtAuthFilter: true
            
            JwtAuthFilter->>JwtTokenProvider: getUsernameFromToken(jwt)
            JwtTokenProvider-->>JwtAuthFilter: username
            
            JwtAuthFilter->>UserDetailsService: loadUserByUsername(username)
            UserDetailsService-->>JwtAuthFilter: UserDetails
            
            JwtAuthFilter->>SecurityContext: setAuthentication(userDetails)
            JwtAuthFilter->>ProtectedEndpoint: Continue to endpoint
            ProtectedEndpoint-->>Client: 200 OK with data
            
        else Token invalid/expired
            JwtTokenProvider-->>JwtAuthFilter: false
            JwtAuthFilter->>ProtectedEndpoint: Continue without authentication
            ProtectedEndpoint-->>Client: 401 Unauthorized
        end
        
    else No token
        JwtAuthFilter->>ProtectedEndpoint: Continue without authentication
        ProtectedEndpoint-->>Client: 401 Unauthorized
    end
```

---

## Flow Descriptions

### 1. User Registration Flow
1. User fills registration form on web UI
2. Client-side validation checks input
3. POST request sent to `/api/auth/register`
4. Backend validates uniqueness of username and email
5. Password is encrypted using BCrypt
6. User entity is saved to database
7. Success message returned to client
8. User redirected to login page

### 2. User Login Flow
1. User enters credentials on login page
2. POST request sent to `/api/auth/login`
3. Spring Security's AuthenticationManager validates credentials
4. Password is checked using BCrypt
5. If valid, JWT token is generated
6. Token and user info returned to client
7. Client stores token in localStorage
8. User redirected to dashboard

### 3. Get Current User Profile Flow
1. User accesses protected dashboard page
2. JWT token sent in Authorization header
3. JwtAuthenticationFilter intercepts request
4. Token is validated and username extracted
5. Authentication set in SecurityContext
6. UserController retrieves user from database
7. User data (excluding password) returned to client
8. Profile information displayed to user

### 4. User Logout Flow
1. User clicks logout button
2. Client removes token from localStorage
3. Client clears all user data
4. User redirected to login page
5. Protected routes no longer accessible

### 5. JWT Token Validation Flow
1. Every request to protected endpoint includes JWT
2. Filter extracts and validates token
3. If valid, user is authenticated for the request
4. If invalid/expired, 401 Unauthorized returned
5. SecurityContext cleared after request

---

## Key Security Points

- **Password Security**: Passwords never transmitted or stored in plain text
- **Token Security**: JWT tokens expire after 24 hours
- **Stateless Authentication**: No server-side session storage
- **Request Filtering**: All requests pass through JWT filter
- **CORS Protection**: Only configured origins allowed

---

**Document Version**: 1.0  
**Last Updated**: February 7, 2026  
**Author**: G5 - Canadilla
