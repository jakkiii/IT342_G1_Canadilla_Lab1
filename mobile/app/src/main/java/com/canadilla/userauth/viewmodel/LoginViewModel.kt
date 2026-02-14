package com.canadilla.userauth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canadilla.userauth.model.LoginRequest
import com.canadilla.userauth.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {
    
    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState
    
    fun login(usernameOrEmail: String, password: String) {
        // Validation
        if (usernameOrEmail.isBlank() || password.isBlank()) {
            _loginState.value = LoginState.Error("Please fill in all fields")
            return
        }
        
        if (password.length < 6) {
            _loginState.value = LoginState.Error("Password must be at least 6 characters")
            return
        }
        
        _loginState.value = LoginState.Loading
        
        viewModelScope.launch {
            val request = LoginRequest(usernameOrEmail, password)
            val result = authRepository.login(request)
            
            result.onSuccess { response ->
                _loginState.value = LoginState.Success
            }.onFailure { exception ->
                _loginState.value = LoginState.Error(
                    exception.message ?: "Login failed. Please check your credentials."
                )
            }
        }
    }
    
    sealed class LoginState {
        object Loading : LoginState()
        object Success : LoginState()
        data class Error(val message: String) : LoginState()
    }
}
