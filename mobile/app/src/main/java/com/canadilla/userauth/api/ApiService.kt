package com.canadilla.userauth.api

import com.canadilla.userauth.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<MessageResponse>
    
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>
    
    @POST("auth/logout")
    suspend fun logout(): Response<MessageResponse>
    
    @GET("user/me")
    suspend fun getCurrentUser(@Header("Authorization") token: String): Response<UserResponse>
}
