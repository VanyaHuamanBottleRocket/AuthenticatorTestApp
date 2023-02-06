package com.example.authenticatortestapp

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Route

class TokenAuthenticator(private val tokenManager: TokenManager) : Authenticator {
    private val authMutex = Mutex()

    override fun authenticate(route: Route?, response: okhttp3.Response): Request? {
        return runBlocking {
            val authToken = getAuthToken()
            if (!authToken.isNullOrBlank()) {
                response.request.newBuilder()
                    .header("Authorization", "Bearer $authToken")
                    .build()
            } else {
                null
            }
        }
    }

    private suspend fun getAuthToken(): String? {
        return authMutex.withLock {
            tokenManager.getAuthToken()
        }
    }
}
