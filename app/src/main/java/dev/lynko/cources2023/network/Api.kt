package dev.lynko.cources2023

import android.util.Log
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

interface NasaApi {
//    @GET("planetary/apod?api_key=$API_KEY")
//    suspend fun getPlanetary(): ApiData

    @GET
    fun getPlanetaryOtherMethod(@Url url: String): Call<ApiResult>

}

object NasaApiImpl {

//    private val moshi = Moshi.Builder()
//        .add(KotlinJsonAdapterFactory())
//        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.nasa.gov/")
        .client(
            OkHttpClient.Builder()
                .connectTimeout(10 ,TimeUnit.SECONDS)
                .writeTimeout(1, TimeUnit.MINUTES)
//                .addInterceptor(NasaInterceptor())
                .build()
        )
        .build()



    private val nasaApiService = retrofit.create(NasaApi::class.java)

    suspend fun getPlanetary(): ApiResult? {
        return withContext(Dispatchers.IO) {
            val result = nasaApiService.getPlanetaryOtherMethod(getUrl()).execute()
            with(result) {
                if (isSuccessful) {
                    ApiResult(
                        date = body()?.date,
                        explanation = body()?.explanation,
                        url = body()?.url,
                        title = body()?.title
                    )
                } else {
                    null
                }
            }
        }
    }


    private fun getUrl(): String {
        return "planetary/apod?api_key=$API_KEY"

    }

    const val TAG = "NasaApiImpl"
}