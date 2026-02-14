# Android User Auth Mobile App

This is the Android mobile application for the User Authentication System.

## Tech Stack
- **Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **Networking**: Retrofit + OkHttp
- **Security**: EncryptedSharedPreferences
- **UI**: Material Design Components

## Features
- User Registration
- User Login with JWT Authentication
- Protected Dashboard/Profile Screen
- Secure Token Storage
- Logout Functionality

## Project Structure
```
app/
├── src/main/
│   ├── java/com/canadilla/userauth/
│   │   ├── api/              # Retrofit API interfaces
│   │   ├── model/            # Data models and DTOs
│   │   ├── repository/       # Repository layer
│   │   ├── ui/               # Activities
│   │   └── viewmodel/        # ViewModels
│   ├── res/                  # Resources (layouts, strings, etc.)
│   └── AndroidManifest.xml
└── build.gradle
```

## Setup Instructions

### Prerequisites
- Android Studio Arctic Fox or later
- JDK 17
- Android SDK 34
- Minimum Android version: 7.0 (API 24)

### Backend Configuration
The app connects to the Spring Boot backend running on:
- **Emulator**: `http://10.0.2.2:8080`
- **Physical Device**: Update IP in `RetrofitClient.kt` to your computer's local IP

### Running the App

1. **Open in Android Studio**
   ```bash
   cd mobile
   # Open this folder in Android Studio
   ```

2. **Sync Gradle**
   - Let Android Studio download dependencies

3. **Start Backend**
   - Ensure your Spring Boot backend is running on port 8080

4. **Run on Emulator**
   - Create/start an Android Virtual Device (AVD)
   - Click Run button in Android Studio

5. **Run on Physical Device**
   - Enable USB debugging
   - Update BASE_URL in `RetrofitClient.kt`:
     ```kotlin
     private const val BASE_URL = "http://YOUR_COMPUTER_IP:8080/api/"
     ```
   - Connect device and run

## API Endpoints Used
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login user
- `POST /api/auth/logout` - Logout user
- `GET /api/user/me` - Get current user profile

## Security Features
- JWT Token Authentication
- Encrypted SharedPreferences for token storage
- Password masking in UI
- HTTPS support (when backend uses SSL)

## Troubleshooting

### Network Issues
- Verify backend is running
- Check firewall settings
- For physical device, ensure phone and computer are on same network

### Build Errors
- Clean and rebuild: `Build > Clean Project` then `Build > Rebuild Project`
- Invalidate caches: `File > Invalidate Caches / Restart`

## Testing Credentials
Use the same credentials you created via web or register new ones:
- Username: testuser
- Password: password123

## Screenshots
Screenshots will be added to `/docs` folder for FRS documentation.
