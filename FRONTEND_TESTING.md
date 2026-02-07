# Frontend Setup and Testing Instructions

## Prerequisites
- Node.js must be installed (if not, you cannot run the frontend)
- Backend must be running on http://localhost:8080

## Step 1: Install Dependencies

Open terminal in VS Code and run:

```powershell
cd web
npm install
```

**Wait for installation to complete** (may take 2-5 minutes)

## Step 2: Start React Development Server

```powershell
npm start
```

The web app will automatically open in your browser at http://localhost:3000

## Step 3: Test the Application

### Test Registration Flow:
1. Browser should show Login page by default
2. Click "Register here" link
3. Fill in the registration form:
   - Username: `john_doe`
   - Email: `john@example.com`
   - Password: `password123`
   - First Name: `John`
   - Last Name: `Doe`
   - Phone: `1234567890`
4. Click "Register" button
5. **Expected:** Redirected to Login page with success message

### Test Login Flow:
1. On Login page, enter:
   - Username/Email: `john_doe` (or the username you registered)
   - Password: `password123`
2. Click "Login" button
3. **Expected:** Redirected to Dashboard showing your profile

### Test Dashboard/Profile:
1. After login, you should see:
   - Your User ID
   - Username
   - Email
   - First Name, Last Name
   - Phone Number
   - Member Since date
   - Logout button

### Test Protected Route:
1. Open new browser tab
2. Try to access: http://localhost:3000/dashboard
3. **Expected:** Should redirect to login page (because not authenticated)
4. Log in, then should access dashboard normally

### Test Logout:
1. From Dashboard, click "Logout" button
2. **Expected:** Redirected to Login page
3. Try accessing http://localhost:3000/dashboard again
4. **Expected:** Should redirect to Login page

## Taking Screenshots for Documentation

### Required Screenshots:

1. **Register Page**
   - URL: http://localhost:3000/register
   - Show the registration form (empty or filled)
   - Press `Windows + Shift + S` to capture
   - Save as: `docs/screenshots/01-register.png`

2. **Login Page**
   - URL: http://localhost:3000/login
   - Show the login form
   - Save as: `docs/screenshots/02-login.png`

3. **Dashboard/Profile Page**
   - URL: http://localhost:3000/dashboard (after logging in)
   - Show user information displayed
   - Save as: `docs/screenshots/03-dashboard.png`

4. **Logout Action**
   - Show the Dashboard with Logout button visible (before clicking)
   - Or show being redirected to Login after logout
   - Save as: `docs/screenshots/04-logout.png`

## Common Issues

### "npm is not recognized"
- Node.js is not installed or not in PATH
- Ask your instructor for help installing Node.js

### "Cannot find module" errors
- Run `npm install` again in the web directory

### "Port 3000 already in use"
- Another app is using port 3000
- Stop that app or change port
- To use a different port: `set PORT=3001 && npm start`

### Backend connection errors
- Make sure backend is running on port 8080
- Check browser console (F12) for error messages
- Verify CORS is configured properly in backend

### Login fails with correct password
- Check browser console (F12) for error messages
- Verify backend is running
- Try registering a new user
- Check MySQL that user exists and password is encrypted

## Success Checklist ✓
- [ ] npm install completed without errors
- [ ] React app starts and opens in browser
- [ ] Can access http://localhost:3000
- [ ] Register page displays correctly
- [ ] Can register a new user
- [ ] Can login with registered credentials
- [ ] Dashboard shows user information
- [ ] Logout button works
- [ ] Protected routes redirect to login when not authenticated
- [ ] All 4 screenshots captured
