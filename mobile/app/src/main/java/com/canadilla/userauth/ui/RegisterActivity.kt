package com.canadilla.userauth.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.canadilla.userauth.databinding.ActivityRegisterBinding
import com.canadilla.userauth.repository.AuthRepository
import com.canadilla.userauth.repository.SessionManager
import com.canadilla.userauth.viewmodel.RegisterViewModel
import com.canadilla.userauth.viewmodel.ViewModelFactory

class RegisterActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sessionManager: SessionManager
    private lateinit var authRepository: AuthRepository
    
    private val registerViewModel: RegisterViewModel by viewModels {
        ViewModelFactory(authRepository)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        sessionManager = SessionManager(this)
        authRepository = AuthRepository(sessionManager)
        
        setupClickListeners()
        observeViewModel()
    }
    
    private fun setupClickListeners() {
        binding.btnRegister.setOnClickListener {
            val firstName = binding.etFirstName.text.toString().trim()
            val lastName = binding.etLastName.text.toString().trim()
            val username = binding.etUsername.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val phoneNumber = binding.etPhoneNumber.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            
            registerViewModel.register(username, email, password, firstName, lastName, phoneNumber)
        }
        
        binding.tvLoginLink.setOnClickListener {
            finish()
        }
    }
    
    private fun observeViewModel() {
        registerViewModel.registerState.observe(this) { state ->
            when (state) {
                is RegisterViewModel.RegisterState.Loading -> {
                    showLoading(true)
                }
                is RegisterViewModel.RegisterState.Success -> {
                    showLoading(false)
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                    // Navigate back to login
                    finish()
                }
                is RegisterViewModel.RegisterState.Error -> {
                    showLoading(false)
                    Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.btnRegister.isEnabled = !isLoading
        binding.etFirstName.isEnabled = !isLoading
        binding.etLastName.isEnabled = !isLoading
        binding.etUsername.isEnabled = !isLoading
        binding.etEmail.isEnabled = !isLoading
        binding.etPhoneNumber.isEnabled = !isLoading
        binding.etPassword.isEnabled = !isLoading
    }
}
