package com.example.authenticatortestapp

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import java.time.LocalDateTime

class CustomSharedPreferences(context: Context) {

    private var masterKey: MasterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private var sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        "secret_shared_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    // use the shared preferences and editor as you normally would
    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun saveTokenResponse(tokenResponse: TokenResponse) {
        editor.putString(AUTH_TOKEN, tokenResponse.authToken).apply()
        editor.putString(REFRESH_TOKEN, tokenResponse.refreshToken).apply()
        editor.putString(TIME_TO_LIVE, tokenResponse.timeToLive).apply()
    }

    fun getTokenResponse(): TokenResponse {
        return TokenResponse(
            sharedPreferences.getString(AUTH_TOKEN, null),
            sharedPreferences.getString(REFRESH_TOKEN, null),
            sharedPreferences.getString(TIME_TO_LIVE, null)
        )

    }

    fun saveAuthToken(authToken: String?) {
        editor.putString(AUTH_TOKEN, authToken)
    }

    fun getAuthToken(): String? {
        return sharedPreferences.getString(AUTH_TOKEN, null)
    }

    fun saveRefreshToken(refreshToken: String?) {
        editor.putString(REFRESH_TOKEN, refreshToken)
    }

    fun getRefreshToken(): String? {
        return sharedPreferences.getString(REFRESH_TOKEN, null)
    }

    fun saveTimeToLive(timeToLive: String?) {
        editor.putString(TIME_TO_LIVE, timeToLive)
    }

    fun getTimeToLive(): String? {
        return sharedPreferences.getString(TIME_TO_LIVE, null)
    }

    companion object {
        const val AUTH_TOKEN = "auth_token"
        const val REFRESH_TOKEN = "refresh_token"
        const val TIME_TO_LIVE = "time_to_live"
    }
}
