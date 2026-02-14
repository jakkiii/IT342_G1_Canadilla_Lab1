package com.canadilla.userauth.repository

import com.canadilla.userauth.api.RetrofitClient
import com.canadilla.userauth.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository(private val sessionManager: SessionManager) {
    
    private val apiService = RetrofitClient.apiService
    
    suspend fun register(request: RegisterRequest): Result<MessageResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.register(request)
                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception(response.message() ?: "Registration failed"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
    
    suspend fun login(request: LoginRequest): Result<AuthResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.login(request)
                if (response.isSuccessful && response.body() != null) {
                    val authResponse = response.body()!!
                    
                    // Save token first
                    sessionManager.saveAuthToken(authResponse.token)
                    
                    // Fetch full user details after login - MUST succeed
                    try {
                        val userResponse = apiService.getCurrentUser("Bearer ${authResponse.token}")
                        if (userResponse.isSuccessful && userResponse.body() != null) {
                            val userInfo = userResponse.body()!!
                            val user = User(
                                id = userInfo.id,
                                username = userInfo.username,
                                email = userInfo.email,
                                firstName = userInfo.firstName,
                                lastName = userInfo.lastName,
                                token = authResponse.token
                            )
                            sessionManager.saveUser(user)
                            Result.success(authResponse)
                        } else {
                            // Failed to get user details - clear token and fail
                            sessionManager.clearSession()
                            Result.failure(Exception("Failed to fetch user profile"))
                        }
                    } catch (e: Exception) {
                        // Network error getting user details - clear token and fail
                        sessionManager.clearSession()
                        Result.failure(Exception("Cannot connect to server: ${e.message}"))
                    }
                } else {
                    Result.failure(Exception(response.message() ?: "Login failed"))
                }
            } catch (e: Exception) {
                // Clear any partial session data
                sessionManager.clearSession()
                Result.failure(Exception("Network error: ${e.message}"))
            }
        }
    }
    
    suspend fun logout(): Result<MessageResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.logout()
                sessionManager.clearSession()
                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!)
                } else {
                    Result.success(MessageResponse("Logged out"))
                }
            } catch (e: Exception) {
                sessionManager.clearSession()
                Result.success(MessageResponse("Logged out"))
            }
        }
    }
    
    suspend fun getCurrentUser(): Result<UserResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val token = sessionManager.getAuthToken()
                if (token == null) {
                    return@withContext Result.failure(Exception("No authentication token"))
                }
                
                val response = apiService.getCurrentUser("Bearer $token")
                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception(response.message() ?: "Failed to fetch user"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
