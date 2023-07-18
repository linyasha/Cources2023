package dev.lynko.cources2023

import android.util.Log
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface NasaApi{
    @GET("planetary/apod?api_key=$API_KEY")
    suspend fun getPlanetary(): ApiData



    @GET("DONKI/FLR?startDate=yyyy-MM-dd&endDate=yyyy-MM-dd&api_key=$API_KEY ")
    suspend fun someOther(): ApiData
//    suspend fun getPlanetary(): Response<RequestBody>
}

object NasaApiImpl {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl("https://api.nasa.gov/")
        .build()

    private val nasaApiService = retrofit.create(NasaApi::class.java)

    suspend fun getPlanetary(): String {
        return withContext(Dispatchers.IO) {
            nasaApiService.getPlanetary().result
        }
    }

}