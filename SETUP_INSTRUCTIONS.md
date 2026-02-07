# Backend Setup Instructions

## Step 1: Start MySQL in XAMPP
1. Open XAMPP Control Panel
2. Click "Start" next to MySQL
3. Verify MySQL is running (shows green highlight)

## Step 2: Run Backend

### Using IntelliJ IDEA (Recommended):
1. Open IntelliJ IDEA
2. File → Open → Select the `backend` folder
3. Wait for Maven to download dependencies (check bottom right)
4. Navigate to: `src/main/java/com/canadilla/userauth/UserAuthApplication.java`
5. Right-click on the file → Run 'UserAuthApplication'
6. Wait for console message: "Started UserAuthApplication in X seconds"
7. Backend is now running on http://localhost:8080

### Using Maven Command Line (Alternative):
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

## Step 3: Verify Backend is Running

### Check Console Output:
You should see:
```
Started UserAuthApplication in 5.234 seconds (process running for 5.678)
```

### Test Endpoints:
Open a new terminal and test:

**Test 1 - Register a user:**
```bash
curl -X POST http://localhost:8080/api/auth/register -H "Content-Type: application/json" -d "{\"username\":\"testuser\",\"email\":\"test@example.com\",\"password\":\"password123\",\"firstName\":\"Test\",\"lastName\":\"User\",\"phoneNumber\":\"1234567890\"}"
```

Expected response:
```json
{"message":"User registered successfully!"}
```

**Test 2 - Login:**
```bash
curl -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d "{\"usernameOrEmail\":\"testuser\",\"password\":\"password123\"}"
```

Expected response (will include JWT token):
```json
{"token":"eyJhbGc...", "id":1, "username":"testuser", "email":"test@example.com"}
```

## Step 4: Check Database

1. Open phpMyAdmin (click Admin next to MySQL in XAMPP)
2. Click on `user_auth_db` database in left sidebar
3. You should see a `users` table
4. Click on `users` table
5. You should see the test user you registered
6. **Verify password is encrypted** (should look like: `$2a$10$...`)

## Troubleshooting

### Error: "Communications link failure"
- Make sure MySQL is running in XAMPP
- Check port 3306 is not blocked

### Error: "Access denied for user 'root'"
- Check your MySQL password in XAMPP
- If you have a password, update `application.properties`

### Error: Port 8080 already in use
- Stop any other application using port 8080
- Or change port in `application.properties`: `server.port=8081`

### Maven dependencies not downloading
- Check internet connection
- In IntelliJ: File → Invalidate Caches → Restart
- Or run: `mvn clean install -U`

## Success Indicators ✓
- [ ] MySQL is running in XAMPP
- [ ] Backend starts without errors
- [ ] Console shows "Started UserAuthApplication"
- [ ] Can access http://localhost:8080
- [ ] Can register a user successfully
- [ ] Can login successfully
- [ ] Database shows encrypted passwords
- [ ] Users table exists in MySQL
