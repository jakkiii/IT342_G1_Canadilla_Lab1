# IT342 G1 Canadilla - User Authentication System
## Full-Stack User Registration and Authentication (Labs 1-3 Complete)

### 📋 Project Overview
This project implements a complete full-stack User Registration and Authentication system with:
- **Backend**: Spring Boot 3.2 REST API with JWT Authentication
- **Web Application**: ReactJS with React Router
- **Mobile Application**: Android Kotlin with MVVM Architecture ✅ COMPLETE

### 🏗️ Project Structure
```
IT342_G1_Canadilla_Lab1/
├── /backend          # Spring Boot REST API (Java 17)
├── /web              # React Web Application
├── /mobile           # Android Kotlin Mobile App ✅
├── /docs             # Complete Documentation
│   ├── screenshots/  # Web & Mobile UI screenshots
│   ├── *.md files    # Setup guides
│   └── FRS.pdf       # Functional Requirements Specification
├── README.md         # This file
└── TASK_CHECKLIST.md # Detailed progress tracking
```

### 🚀 Quick Start

#### 1. Backend (Spring Boot)
```powershell
cd backend
mvn spring-boot:run
```
✅ API runs on: `http://localhost:8080`

#### 2. Web Application (React)
```powershell
cd web
npm install
npm start
```
✅ Web app opens at: `http://localhost:3000`

#### 3. Mobile Application (Android Kotlin)

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/api/auth/register` | Register new user | ❌ No |
| POST | `/api/auth/login` | Login user & get JWT | ❌ No |
| POST | `/api/auth/logout` | Logout user | ❌ No |
| GET | `/api/user/me` | Get current user profile | ✅ Yes (JWT) |
4. Click Run (▶️) button
5. Select emulator or connected device

✅ App installs and launches automatically

**📖 Detailed Guides:**
- [Quick Start Guide](docs/QUICK_START.md) - 5-minute setup
- [Mobile Setup Guide](docs/MOBILE_SETUP_GUIDE.md) - Complete Android setup
- [Lab 3 Completion Guide](docs/LAB3_COMPLETION_GUIDE.md) - Step-by-step testing

### 📡 API Endpoints
- `POST /api/auth/register` - User registration
- `POST /api/auth/login` - User login
- `GET /api/user/me` - Get current user (protected)

### 🗄️ Database Configuration
- **Type**: MySQL 8.0
- **Database Name**: `user_auth_db`
- **Port**: 3306
- **Auto-create**: Tables created automatically by Hibernate

### 🔒 Security Features
- **Password Encryption**: BCrypt hashing
- **Authentication**: JWT (JSON Web Tokens)
- **Secure Storage**: 
  - Web: localStorage with token management
  - Mobile: EncryptedSharedPreferences
- **Protected Routes**: JWT validation middleware
- **CORS**: Configured for web and mobile access

### �️ Technologies Used

#### Backend
- Java 17
- Spring Boot 3.2
- Spring Security
- JWT (jjwt)
- MySQL 8.0
- Maven

#### Web
- React 18
- React Router 6
- Axios
- CSS3

#### Mobile
- Kotlin
- Android SDK 34 (min: 24)
- Retrofit 2
- Coroutines
- Material Design 3
- MVVM Architecture
- EncryptedSharedPreferences

### ✅ Features Implemented

#### Registration
- ✅ Form validation (username, email, password, full name)
- ✅ Email format validation
- ✅ Password strength checking
- ✅ Duplicate username/email detection
- ✅ Success/error feedback

#### Login
- ✅ Username or email login
- ✅ JWT token generation
- ✅ Secure token storage
- ✅ Session persistence
- ✅ Auto-redirect if already logged in

#### Dashboard/Profile
- ✅ Display user information (ID, username, email, full name)
- ✅ Protected route (requires authentication)
- ✅ JWT validation
- ✅ Auto-logout on invalid token

#### Logout
- ✅ Clear session/token
- ✅ Confirmation dialog (mobile)
- ✅ Redirect to login
- ✅ Server-side endpoint

### 📚 Documentation
See [`/docs`](docs/) folder for:
- ✅ Functional Requirements Specification (FRS) with screenshots
- ✅ Entity Relationship Diagram (ERD)
- ✅ UML Class Diagrams
- ✅ UML Sequence Diagrams
- ✅ Web UI Screenshots
- ✅ Mobile UI Screenshots
- ✅ Setup Guides (Backend, Web, Mobile)
- ✅ API Testing Guide

### 📸 Screenshots
- **Web Application**: [`docs/screenshots/web/`](docs/screenshots/web/)
- **Mobile Application**: [`docs/screenshots/mobile/`](docs/screenshots/mobile/)

### 🧪 Testing
```powershell
# Test backend endpoints
.\test-backend.bat

# Test web application
cd web
npm test

# Test mobile application
# Use Android Studio built-in emulator
```

### 🎯 Lab Completion Status
- ✅ **Lab 1**: Backend API with JWT authentication
- ✅ **Lab 2**: React web application
- ✅ **Lab 3**: Android Kotlin mobile application

### 👨‍💻 Author
**G1 - Canadilla**

### 📅 Last Updated
February 14, 2026

---

## 🚨 Common Issues & Solutions

### Backend Issues
```powershell
# Port 8080 already in use
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# MySQL not running
# Start MySQL in XAMPP Control Panel
```

### Web Issues
```powershell
# Dependencies issues
rm -r node_modules package-lock.json
npm install
```

### Mobile Issues
```powershell
# Cannot connect to backend from emulator
# Use 10.0.2.2:8080 instead of localhost:8080 (already configured)

# For physical device, update RetrofitClient.kt:
# private const val BASE_URL = "http://YOUR_LOCAL_IP:8080/api/"
```

---

## 📞 Support
For detailed setup instructions, see:
- [Quick Start Guide](docs/QUICK_START.md)
- [Mobile Setup Guide](docs/MOBILE_SETUP_GUIDE.md)
- [Lab 3 Completion Guide](docs/LAB3_COMPLETION_GUIDE.md)
- [Task Checklist](TASK_CHECKLIST.md)
