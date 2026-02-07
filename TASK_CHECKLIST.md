# Task Checklist - IT342 Lab 1
## User Registration and Authentication System

**Project**: IT342_G5_Canadilla_Lab1  
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

---

## 🔄 IN-PROGRESS

### Testing & Screenshots
- [ ] Test user registration flow
- [ ] Test user login flow  
- [ ] Test protected dashboard access
- [ ] Test logout functionality
- [ ] Verify password encryption in database
- [ ] Take screenshot of Register page
- [ ] Take screenshot of Login page
- [ ] Take screenshot of Dashboard/Profile page
- [ ] Take screenshot demonstrating logout

---

## 📝 TODO

### Documentation Tasks
- [ ] Update FRS document with screenshots (or use screenshots/README.md)
- [ ] Convert FRS to PDF (if required)

### GitHub Tasks
- [ ] Create public GitHub repository "IT342_G5_Canadilla_Lab1"
- [ ] Initialize Git in project folder
- [ ] Create .gitignore file
- [ ] Commit all files: `git commit -m "Initial commit: Backend and Web implementation"`
- [ ] Push to GitHub: `git push origin main`
- [ ] Verify repository is public

### Final Submission
- [ ] Verify all required features are working
- [ ] Update README.md with final instructions
- [ ] Submit GitHub repository link in MS Teams
- [ ] Create submission document with:
  - GitHub repository URL
  - Project description
  - Setup instructions
  - Screenshots

### Mobile Application (Lab 2 - Not Required Yet)
- [ ] Set up React Native or Flutter project
- [ ] Implement mobile registration page
- [ ] Implement mobile login page
- [ ] Implement mobile dashboard
- [ ] Connect mobile app to backend API

---

## 📊 Progress Summary

| Category | Total Tasks | Completed | In Progress | Todo |
|----------|-------------|-----------|-------------|------|
| **Project Setup** | 3 | 3 | 0 | 0 |
| **Backend** | 17 | 17 | 0 | 0 |
| **Web App** | 10 | 10 | 0 | 0 |
| **Documentation** | 11 | 11 | 0 | 0 |
| **Environment Setup** | 9 | 9 | 0 | 0 |
| **Testing & Screenshots** | 9 | 0 | 9 | 0 |
| **GitHub** | 5 | 0 | 0 | 5 |
| **Submission** | 4 | 0 | 0 | 4 |
| **Mobile (Lab 2)** | 5 | 0 | 0 | 5 |
| **TOTAL** | **73** | **50** | **9** | **14** |

**Overall Progress**: 68% Complete

---

## 🔧 Setup Instructions

### Prerequisites
1. **Java 17** - Download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
2. **Maven 3.8+** - Download from [Apache Maven](https://maven.apache.org/download.cgi)
3. **MySQL 8.0** - Download from [MySQL](https://dev.mysql.com/downloads/)
4. **Node.js 18+ and npm** - Download from [Node.js](https://nodejs.org/)
5. **Git** - Download from [Git](https://git-scm.com/downloads)

### Database Setup
```sql
-- Create database (will be auto-created by Spring Boot if configured)
CREATE DATABASE user_auth_db;

-- Or configure in application.properties with createDatabaseIfNotExist=true
```

### Backend Setup
```bash
cd backend
mvn clean install
mvn spring-boot:run
# Backend runs on http://localhost:8080
```

### Web App Setup
```bash
cd web
npm install
npm start
# Web app runs on http://localhost:3000
```

---

## 🐛 Known Issues
- None currently

---

## 📝 Notes
- Mobile application will be implemented in Lab 2
- All passwords are encrypted using BCrypt
- JWT tokens expire after 24 hours
- CORS is configured for http://localhost:3000

---

## 🎯 Commit History Template

Use these commit message formats:
```
feat: Add user registration endpoint
fix: Resolve CORS issue on login
docs: Update FRS with ERD diagram
style: Format React components
test: Add unit tests for AuthService
```

---

**Repository URL**: (To be added after GitHub creation)  
**Submission Date**: (To be filled)  
**Grade**: (To be filled by instructor)
