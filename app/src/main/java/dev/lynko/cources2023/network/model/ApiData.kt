package dev.lynko.cources2023

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//
//@JsonClass(generateAdapter = true)
//data class ApiData(
//    @Json(name = "url") val result: String
////    @Json(name = "title") val title: String
//)

data class ApiResult(
    @SerializedName("date") val date: String?,
    @SerializedName("explanation") val explanation: String?,
    @SerializedName("hdurl") val url: String?,
    @SerializedName("title") val title: String?
)