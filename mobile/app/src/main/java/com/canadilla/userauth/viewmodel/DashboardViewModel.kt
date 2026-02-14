package com.canadilla.userauth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canadilla.userauth.model.UserResponse
import com.canadilla.userauth.repository.AuthRepository
import kotlinx.coroutines.launch

class DashboardViewModel(private val authRepository: AuthRepository) : ViewModel() {
    
    private val _userState = MutableLiveData<UserState>()
    val userState: LiveData<UserState> = _userState
    
    private val _logoutState = MutableLiveData<LogoutState>()
    val logoutState: LiveData<LogoutState> = _logoutState
    
    fun loadUserProfile() {
        _userState.value = UserState.Loading
        
        viewModelScope.launch {
            val result = authRepository.getCurrentUser()
            
            result.onSuccess { user ->
                _userState.value = UserState.Success(user)
            }.onFailure { exception ->
                _userState.value = UserState.Error(
                    exception.message ?: "Failed to load user profile"
                )
            }
        }
    }
    
    fun logout() {
        _logoutState.value = LogoutState.Loading
        
        viewModelScope.launch {
            val result = authRepository.logout()
            
            result.onSuccess {
                _logoutState.value = LogoutState.Success
            }.onFailure { exception ->
                // Even if logout fails, clear session
                _logoutState.value = LogoutState.Success
            }
        }
    }
    
    sealed class UserState {
        object Loading : UserState()
        data class Success(val user: UserResponse) : UserState()
        data class Error(val message: String) : UserState()
    }
    
    sealed class LogoutState {
        object Loading : LogoutState()
        object Success : LogoutState()
    }
}
