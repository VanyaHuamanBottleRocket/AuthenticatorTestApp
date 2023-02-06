package com.example.authenticatortestapp

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: GitHubRepository
) : BaseViewModel() {

    fun makeCalls() {
        viewModelScope.launch {
            repository.getGigyaTestSample()
            repository.getJavaScriptTest()
        }
    }
}
