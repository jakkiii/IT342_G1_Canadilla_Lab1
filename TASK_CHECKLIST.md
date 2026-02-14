# Task Checklist - IT342 Lab 1
## User Registration and Authentication System

**Project**: IT342_G1_Canadilla_Lab1  
**Last Updated**: February 7, 2026

---

## ✅ DONE

### Project Setup
- [x] Created project folder structure (/backend, /web, /mobile, /docs)
- [x] Created README.md with project overview
- [x] Initialized Git repository

### Backend Development (Spring Boot)
- [x] Created Maven project with pom.xml
- [x] Configured application.properties (database, JWT, CORS)
- [x] Created User entity class
- [x] Created UserRepository interface
- [x] Created DTOs (RegisterRequest, LoginRequest, AuthResponse, UserResponse, MessageResponse)
- [x] Implemented JwtTokenProvider utility class
- [x] Implemented JwtAuthenticationFilter
- [x] Implemented CustomUserDetailsService
- [x] Configured SecurityConfig with BCrypt password encoding
- [x] Implemented AuthService (register and login logic)
- [x] Implemented UserService (get current user)
- [x] Created AuthController (POST /api/auth/register, POST /api/auth/login)
- [x] Created UserController (GET /api/user/me)
- [x] Configured CORS for React web app

### Web Application Development (React)
- [x] Created React project structure
- [x] Created package.json with dependencies
- [x] Configured routing with React Router
- [x] Created authService utility for API calls
- [x] Implemented Register page component
- [x] Implemented Login page component
- [x] Implemented Dashboard/Profile page component
- [x] Implemented PrivateRoute component for protected routes
- [x] Implemented logout functionality
- [x] Added form validation and error handling
- [x] Styled all pages with CSS

### Documentation
- [x] Created FRS (Functional Requirements Specification)
- [x] Created ERD (Entity Relationship Diagram) with Mermaid
- [x] Created UML Class Diagram with Mermaid
- [x] Created UML Sequence Diagrams (Registration, Login, Get User, Logout)
- [x] Created documentation README
- [x] Created TASK_CHECKLIST.md (this file)
- [x] Created screenshots folder in /docs

### Development Environment Setup
- [x] Installed MySQL via XAMPP
- [x] Started MySQL server in XAMPP
- [x] Configured Java 17 SDK in IntelliJ IDEA
- [x] Backend running successfully on http://localhost:8080
- [x] Database tables auto-created by Hibernate
- [x] Installed npm dependencies for React app
- [x] React app running on http://localhost:3000
- [x] Implemented dark metallic theme with Poppins font
- [x] Created .gitignore file

### Additional Setup Documentation
- [x] Created SETUP_INSTRUCTIONS.md for backend setup
- [x] Created FRONTEND_TESTING.md for frontend testing guide
- [x] Created ACTION_PLAN.md for complete workflow
- [x] Created test-backend.bat for automated API testing
- [x] Created MOBILE_SETUP_GUIDE.md for Android setup
- [x] Created LAB3_COMPLETION_GUIDE.md for step-by-step completion

### Mobile Application Development (Android Kotlin)
- [x] Set up Android Studio project with Kotlin
- [x] Configured build.gradle with dependencies (Retrofit, Coroutines, Material Design)
- [x] Created AndroidManifest.xml with permissions and activities
- [x] Implemented data models (User, AuthResponse, LoginRequest, RegisterRequest, etc.)
- [x] Implemented Retrofit API service interface (ApiService.kt)
- [x] Implemented RetrofitClient with OkHttp logging
- [x] Implemented SessionManager with EncryptedSharedPreferences
- [x] Implemented AuthRepository for API calls
- [x] Implemented ViewModels (LoginViewModel, RegisterViewModel, DashboardViewModel)
- [x] Implemented ViewModelFactory for dependency injection
- [x] Created MainActivity with navigation logic
- [x] Created RegisterActivity with form validation
- [x] Created LoginActivity with authentication
- [x] Created DashboardActivity with user profile display
- [x] Implemented logout functionality with confirmation dialog
- [x] Created XML layouts for all activities
- [x] Configured string resources
- [x] Configured CORS in backend for mobile (10.0.2.2)
- [x] Tested on Android emulator
- [x] All features working correctly (Register, Login, Dashboard, Logout)

### Backend Finalization (Lab 3)
- [x] Added logout endpoint (/api/auth/logout)
- [x] Improved error handling in controllers
- [x] Consistent API response format with MessageResponse
- [x] Updated CORS configuration for mobile support

---

## 🔄 IN-PROGRESS

### Final Documentation (Lab 3)
- [x] Take screenshots of Web UI (all screens)
- [x] Take screenshots of Mobile UI (all screens)
- [x] Update FRS PDF with Web screenshots
- [x] Update FRS PDF with Mobile screenshots
- [x] Verify all diagrams match final implementation

---

## 📝 TODO

### Final Submission Preparation
- [x] Commit all changes with proper commit messages
- [x] Push all commits to GitHub repository
- [x] Verify repository structure is complete
- [x] Double-check .gitignore excludes build files
- [x] Final testing of complete system (Web + Mobile + Backend)
