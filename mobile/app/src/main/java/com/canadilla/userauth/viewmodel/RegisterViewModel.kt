package com.canadilla.userauth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canadilla.userauth.model.RegisterRequest
import com.canadilla.userauth.repository.AuthRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val authRepository: AuthRepository) : ViewModel() {
    
    private val _registerState = MutableLiveData<RegisterState>()
    val registerState: LiveData<RegisterState> = _registerState
    
    fun register(username: String, email: String, password: String, firstName: String, lastName: String, phoneNumber: String) {
        // Validation
        if (username.isBlank() || email.isBlank() || password.isBlank() || firstName.isBlank() || lastName.isBlank()) {
            _registerState.value = RegisterState.Error("Please fill in all required fields")
            return
        }
        
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _registerState.value = RegisterState.Error("Please enter a valid email address")
            return
        }
        
        if (password.length < 6) {
            _registerState.value = RegisterState.Error("Password must be at least 6 characters")
            return
        }
        
        _registerState.value = RegisterState.Loading
        
        viewModelScope.launch {
            val request = RegisterRequest(
                username = username,
                email = email,
                password = password,
                firstName = firstName,
                lastName = lastName,
                phoneNumber = phoneNumber.ifBlank { null }
            )
            val result = authRepository.register(request)
            
            result.onSuccess { response ->
                _registerState.value = RegisterState.Success(response.message)
            }.onFailure { exception ->
                _registerState.value = RegisterState.Error(
                    exception.message ?: "Registration failed. Please try again."
                )
            }
        }
    }
    
    sealed class RegisterState {
        object Loading : RegisterState()
        data class Success(val message: String) : RegisterState()
        data class Error(val message: String) : RegisterState()
    }
}
