package com.example.authenticatortestapp

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GigyaTestSampleDto(
    @SerializedName("title")
    val title: String? = null
) : Serializable
