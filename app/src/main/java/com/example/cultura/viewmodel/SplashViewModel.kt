package com.example.cultura.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cultura.data.PreferencesManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashViewModel(private val preferencesManager: PreferencesManager) : ViewModel() {

    private val _isOnboardingComplete = MutableStateFlow(false)
    val isOnboardingComplete: StateFlow<Boolean> get() = _isOnboardingComplete

    init {
        viewModelScope.launch {
            try {
                // Collect the first value from the flow to check onboarding completion
                val completed = preferencesManager.onboardingCompleted.first()
                _isOnboardingComplete.value = completed
            } catch (e: Exception) {
                _isOnboardingComplete.value = false // Default to false if there's an error
            }
        }
    }

    // New method to mark onboarding as complete
    fun completeOnboarding() {
        viewModelScope.launch {
            preferencesManager.setOnboardingComplete(true)
            _isOnboardingComplete.value = true
        }
    }
}
