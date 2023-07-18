package dev.lynko.cources2023

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiData(
    @Json(name = "url") val result: String
//    @Json(name = "title") val title: String
)