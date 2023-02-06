package com.example.authenticatortestapp

import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit

object MyAppModules {
    private const val CONNECTION_TIMEOUT_SECONDS = 30L
    private const val READ_TIMEOUT_SECONDS = 30L
    private const val GIT_HUB_SERVICE_NAME = "git_hub_service_name"
    private const val GIT_HUB_SERVICE = "git_hub_service"
    private const val GIT_HUB_BASE_URL = "https://api.github.com"

    private const val BASE_OK_HTTP_CLIENT_NAME = "baseOkHttpClient"

    private val retrofitModule = module {
        single(named(BASE_OK_HTTP_CLIENT_NAME)) {
            OkHttpClient.Builder()
                .authenticator(TokenAuthenticator(get()))
                .connectTimeout(CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build()
        }

        single(named(GIT_HUB_SERVICE_NAME)) {
            Retrofit.Builder().apply {
                baseUrl(GIT_HUB_BASE_URL)
                client(get(named(BASE_OK_HTTP_CLIENT_NAME)))
            }.build().create(GitHubServiceApi::class.java)
        }
    }

    private val sharedPreferences = module {
        single { CustomSharedPreferences(get()) }
    }

    private val tokenManager = module {
        single { TokenManager(get(), get()) }
    }

    private val repoModule = module {
        single { GitHubRepository(get(named(GIT_HUB_SERVICE_NAME))) }
    }

    private val viewModelModule = module {
        viewModel {
            LoginViewModel(get())
        }
    }

    fun all() = listOf(
        viewModelModule,
        retrofitModule,
        repoModule,
        tokenManager,
        sharedPreferences
    )
}
