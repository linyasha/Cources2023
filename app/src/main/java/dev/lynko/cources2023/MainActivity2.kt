package dev.lynko.cources2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

class MainActivity2 : AppCompatActivity() {


    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val scope = CoroutineScope(Job())



    }

    companion object {
        const val TAG = "MAIN_AC"
    }

}