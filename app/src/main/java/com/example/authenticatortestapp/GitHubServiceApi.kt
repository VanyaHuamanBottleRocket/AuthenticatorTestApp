package com.example.authenticatortestapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface GitHubServiceApi {
    @Headers("Authorization")
    @GET("/vanyahuaman/JavaScriptTest/issues")
    fun getJavaScriptTest(): Call<JavaScriptTestDto>

    @Headers("Authorization")
    @GET("/vanyahuaman/GigyaTestSample/issues")
    fun getGigyaTestSample(): Call<GigyaTestSampleDto>
}
