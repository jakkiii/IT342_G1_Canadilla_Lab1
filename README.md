# IT342 G5 Canadilla - Lab 1
## User Registration and Authentication System

### 📋 Project Overview
This project implements a full-stack User Registration and Authentication system with:
- **Backend**: Spring Boot REST API
- **Web Application**: ReactJS
- **Mobile Application**: (To be implemented in Lab 2)

### 🏗️ Project Structure
```
IT342_G5_Canadilla_Lab1/
├── /backend          # Spring Boot API
├── /web              # React Web Application
├── /mobile           # Mobile App (Lab 2)
├── /docs             # Documentation (FRS, ERD, UML)
├── README.md         # This file
└── TASK_CHECKLIST.md # Progress tracking
```

### 🚀 Getting Started

#### Backend (Spring Boot)
```bash
cd backend
mvn spring-boot:run
```
API runs on: `http://localhost:8080`

#### Web Application (React)
```bash
cd web
npm install
npm start
```
Web app runs on: `http://localhost:3000`

### 📡 API Endpoints
- `POST /api/auth/register` - User registration
- `POST /api/auth/login` - User login
- `GET /api/user/me` - Get current user (protected)

### 🗄️ Database
- **Type**: MySQL
- **Database Name**: user_auth_db
- **Port**: 3306

### 🔒 Security
- Password encryption using BCrypt
- JWT authentication for protected routes

### 📚 Documentation
See `/docs` folder for:
- Functional Requirements Specification (FRS)
- Entity Relationship Diagram (ERD)
- UML Diagrams
- UI Screenshots

### 👨‍💻 Author
G5 - Canadilla

### 📅 Last Updated
February 7, 2026
