package dev.lynko.cources2023

import android.util.Log
import kotlinx.coroutines.*

class Lesson12 {

    val scope = CoroutineScope(Dispatchers.Main)

    fun main(args: Array<String>) {
        Log.d(TAG, "main start:")

        scope.launch {
            Log.d(TAG, "coroutine start")
            updateData()
            Log.d(TAG, "coroutine end")
        }
        Log.d(TAG, "main after launch ")


//        CoroutineScope(Dispatchers.Main).launch {
//
//        }
    }

    suspend fun updateData() {
        Log.d(TAG, "updateData start")
        delay(10000L)
        Log.d(TAG, "updateData end")
    }

    companion object {
        const val TAG = "d_Example"
    }
}