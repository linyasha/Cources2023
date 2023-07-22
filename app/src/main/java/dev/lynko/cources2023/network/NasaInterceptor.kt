package dev.lynko.cources2023.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class NasaInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d(TAG, "intercept: ${chain.request()} ")
        val request = chain.request().newBuilder()
            .url("https://youtube.com")
            .build()
        val responce = chain.proceed(request)
        return responce.also {
            Log.d(TAG, "responce: $it ")
        }
    }

    companion object {
        val TAG = "NasaInterceptor"
    }
}