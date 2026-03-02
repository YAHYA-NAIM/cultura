package com.example.cultura.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cultura.data.PreferencesManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class OnboardingViewModel(private val preferencesManager: PreferencesManager) : ViewModel() {

    private val _isOnboardingComplete = MutableStateFlow(false)
    val isOnboardingComplete: StateFlow<Boolean> get() = _isOnboardingComplete

    init {
        viewModelScope.launch {
            _isOnboardingComplete.value = preferencesManager.onboardingCompleted.first()
        }
    }

    fun completeOnboarding() {
        viewModelScope.launch {
            preferencesManager.setOnboardingComplete(true)
            _isOnboardingComplete.value = true
        }
    }
}
