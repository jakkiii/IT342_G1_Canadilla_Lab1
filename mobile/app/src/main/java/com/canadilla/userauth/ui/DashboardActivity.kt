package com.canadilla.userauth.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.canadilla.userauth.R
import com.canadilla.userauth.databinding.ActivityDashboardBinding
import com.canadilla.userauth.repository.AuthRepository
import com.canadilla.userauth.repository.SessionManager
import com.canadilla.userauth.viewmodel.DashboardViewModel
import com.canadilla.userauth.viewmodel.ViewModelFactory

class DashboardActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var sessionManager: SessionManager
    private lateinit var authRepository: AuthRepository
    
    private val dashboardViewModel: DashboardViewModel by viewModels {
        ViewModelFactory(authRepository)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            binding = ActivityDashboardBinding.inflate(layoutInflater)
            setContentView(binding.root)
            
            sessionManager = SessionManager(this)
            authRepository = AuthRepository(sessionManager)
            
            // Check if user is logged in
            if (!sessionManager.isLoggedIn()) {
                navigateToMain()
                return
            }
            
            setupToolbar()
            setupClickListeners()
            observeViewModel()
            
            // Load user profile
            dashboardViewModel.loadUserProfile()
        } catch (e: Exception) {
            // Handle any unexpected errors
            Toast.makeText(this, "Error loading dashboard: ${e.message}", Toast.LENGTH_LONG).show()
            sessionManager.clearSession()
            navigateToMain()
        }
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }
    
    private fun setupClickListeners() {
        binding.btnLogout.setOnClickListener {
            showLogoutConfirmation()
        }
    }
    
    private fun observeViewModel() {
        dashboardViewModel.userState.observe(this) { state ->
            when (state) {
                is DashboardViewModel.UserState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is DashboardViewModel.UserState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val user = state.user
                    val fullName = "${user.firstName} ${user.lastName}"
                    binding.tvWelcomeMessage.text = getString(R.string.welcome_message, fullName)
                    binding.tvFullName.text = fullName
                    binding.tvUsername.text = user.username
                    binding.tvEmail.text = user.email
                    binding.tvUserId.text = user.id.toString()
                    
                    // Show phone number if available
                    if (!user.phoneNumber.isNullOrBlank()) {
                        binding.llPhoneNumber.visibility = View.VISIBLE
                        binding.tvPhoneNumber.text = user.phoneNumber
                    } else {
                        binding.llPhoneNumber.visibility = View.GONE
                    }
                }
                is DashboardViewModel.UserState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    
                    // If error loading user, try to show cached data
                    val cachedUser = sessionManager.getUser()
                    if (cachedUser != null) {
                        // Show cached data
                        val fullName = "${cachedUser.firstName} ${cachedUser.lastName}"
                        binding.tvWelcomeMessage.text = getString(R.string.welcome_message, fullName)
                        binding.tvFullName.text = fullName
                        binding.tvUsername.text = cachedUser.username
                        binding.tvEmail.text = cachedUser.email
                        binding.tvUserId.text = cachedUser.id.toString()
                        
                        // Show phone number from cached data if available
                        binding.llPhoneNumber.visibility = View.GONE
                        
                        // Show error toast about not being able to refresh
                        Toast.makeText(this, "Showing cached profile data", Toast.LENGTH_SHORT).show()
                    } else {
                        // No cached data and can't load - session corrupted
                        Toast.makeText(this, "Session error. Please login again.", Toast.LENGTH_LONG).show()
                        sessionManager.clearSession()
                        navigateToMain()
                    }
                }
            }
        }
        
        dashboardViewModel.logoutState.observe(this) { state ->
            when (state) {
                is DashboardViewModel.LogoutState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnLogout.isEnabled = false
                }
                is DashboardViewModel.LogoutState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
                    navigateToMain()
                }
            }
        }
    }
    
    private fun showLogoutConfirmation() {
        AlertDialog.Builder(this)
            .setTitle(R.string.logout_button)
            .setMessage(R.string.logout_confirmation)
            .setPositiveButton(R.string.yes) { _, _ ->
                dashboardViewModel.logout()
            }
            .setNegativeButton(R.string.no, null)
            .show()
    }
    
    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
