package dev.lynko.cources2023

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar
import kotlinx.coroutines.*
//import androidx.lifecycle.lifecycleScope
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    val scope = CoroutineScope(Dispatchers.Main)

    var networkJob: Job? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(Lesson12.TAG, "main start:")

        networkJob = scope.launch {
            Log.d(Lesson12.TAG, "coroutine start ${this.hashCode()}  ${scope.hashCode()}")
            launch(Dispatchers.IO) {
                updateData()
            }
//            withContext(Dispatchers.IO) {
//
//            }
            Log.d(Lesson12.TAG, "coroutine end")
        }
        Log.d(Lesson12.TAG, "main after launch ")
    }

    suspend fun updateData() {
        Log.d(Lesson12.TAG, "updateData start")
        delay(1000L)
        Log.d(Lesson12.TAG, "updateData end")
        withContext(Dispatchers.Main) {
            delay(1000L)
            Log.d(TAG, "MAIN: ")
        }
        Log.d(TAG, "updateData: ")
    }

    val TAG = "d_Example"
}