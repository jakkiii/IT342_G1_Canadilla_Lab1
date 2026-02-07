@echo off
echo ============================================
echo   Testing User Auth Backend API
echo ============================================
echo.

echo [1/3] Testing health check...
curl -X GET http://localhost:8080/api/auth/register -v
echo.
echo.

echo [2/3] Testing user registration...
curl -X POST http://localhost:8080/api/auth/register ^
  -H "Content-Type: application/json" ^
  -d "{\"username\":\"testuser\",\"email\":\"test@example.com\",\"password\":\"password123\",\"firstName\":\"Test\",\"lastName\":\"User\",\"phoneNumber\":\"1234567890\"}"
echo.
echo.

echo [3/3] Testing user login...
curl -X POST http://localhost:8080/api/auth/login ^
  -H "Content-Type: application/json" ^
  -d "{\"usernameOrEmail\":\"testuser\",\"password\":\"password123\"}"
echo.
echo.

echo ============================================
echo   Tests completed!
echo ============================================
echo.
echo NOTES:
echo - If you see "Connection refused", backend is not running
echo - If you see "User registered successfully", registration works!
echo - If you see a JWT token, login works!
echo - Run this script again to test with different users
echo.
pause
