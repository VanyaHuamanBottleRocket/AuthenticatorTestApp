package com.example.authenticatortestapp

import kotlinx.coroutines.delay
import okhttp3.ResponseBody
import retrofit2.awaitResponse

class GitHubRepository(
    private val gitHubServiceApi: GitHubServiceApi
) {
    suspend fun getJavaScriptTest(): retrofit2.Response<JavaScriptTestDto> {
        return try {
            val response = gitHubServiceApi.getJavaScriptTest().awaitResponse()
            response
        } catch (e: Exception) {
            retrofit2.Response.error(
                999,
                ResponseBody.create(null, e.message ?: "general error")
            )
        }
    }

    suspend fun getGigyaTestSample(): retrofit2.Response<GigyaTestSampleDto> {
        return try {
            val response = gitHubServiceApi.getGigyaTestSample().awaitResponse()
            response
        } catch (e: Exception) {
            retrofit2.Response.error(
                999,
                ResponseBody.create(null, e.message ?: "general error")
            )
        }
    }

    suspend fun refreshAuthToken(): Result<TokenResponse> {
        delay(DELAY_TIME)
        return Result.success(
            TokenResponse("", "FAKE_REFRESH_TOKEN", "3333")
        )
    }

    companion object {
        const val DELAY_TIME = 5000L
    }
}
