package com.example.authenticatortestapp

import java.time.LocalDateTime

class TokenManager(
    private val customSharedPreferences: CustomSharedPreferences,
    private val repository: GitHubRepository
) {

    suspend fun getAuthToken(): String? {
        return if (authTokenIsValid()) {
            getTokenResponse().authToken
        } else {
            val result = repository.refreshAuthToken()
            if (result.isSuccess && result.getOrNull() != null) {
                result.getOrNull()?.let { saveTokenResponse(it) }
                customSharedPreferences.getAuthToken()
            } else {
                null
            }
        }
    }

    private fun getTokenResponse(): TokenResponse {
        return customSharedPreferences.getTokenResponse()
    }

    private fun saveTokenResponse(tokenResponse: TokenResponse) {
        tokenResponse.timeToLive = LocalDateTime.now().plusMinutes(
            tokenResponse.timeToLive?.toLongOrNull() ?: 0L
        ).toString()
        customSharedPreferences.saveTokenResponse(tokenResponse)
    }

    private fun authTokenIsValid(): Boolean {
        val timeToLive = customSharedPreferences.getTimeToLive()
        return LocalDateTime.parse(timeToLive).isAfter(LocalDateTime.now())
    }
}
