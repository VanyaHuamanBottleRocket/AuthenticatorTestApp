package com.example.authenticatortestapp

data class TokenResponse(
    val authToken: String?,
    val refreshToken: String?,
    var timeToLive: String?
)
