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
import dev.lynko.cources2023.databinding.ActivityMainBinding
import kotlinx.coroutines.*
//import androidx.lifecycle.lifecycleScope
import kotlin.random.Random

class MainActivity : AppCompatActivity() {



    val job = SupervisorJob()
    val coroutineContext = Dispatchers.IO
    val scope = CoroutineScope(coroutineContext)

    private lateinit var binding: ActivityMainBinding


//    var networkJob: Job? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(Lesson12.TAG, "main start:")
        scope.launch {
            var actionStartDownload: Job? = scope.launch(start = CoroutineStart.LAZY) {
                Log.d("MAIN_AC", "onCreate: ${Thread.currentThread().name} ")
                delay(10000L)
                //1
                val result = scope.async {
                    10
                }.await()

            }
            actionStartDownload?.start()
            delay(1000L)
            actionStartDownload?.cancelAndJoin()
            actionStartDownload = null
            actionStartDownload = scope.launch {
                Log.d("MAIN_AC", "onCreate: ${Thread.currentThread().name} ")
            }
            Log.d("MAIN_AC", "job canceled: ")




        }






//        networkJob = scope.launch(Dispatchers.IO) {
//
//
//            //download()
//            //if (success) {
//            //1
//            withContext(Dispatchers.Default) {
//                //2
//                //ui
//                //view's
//                //3
//            }
//            //4
//
//
//            // }
//

//            Log.d(Lesson12.TAG, "coroutine start ${this.hashCode()}  ${scope.hashCode()}")
//            val job = launch(Dispatchers.IO) {
//                updateData()
//            }
////            withContext(Dispatchers.IO) {
////
////            }
//            Log.d(Lesson12.TAG, "coroutine end")
//        }
//        Log.d(Lesson12.TAG, "main after launch ")
    }

    suspend fun updateData() {
        Log.d(Lesson12.TAG, "updateData start")
        delay(1000L)
        something()
        something1()
        Log.d(Lesson12.TAG, "updateData end")
        withContext(Dispatchers.Main) {
            delay(1000L)
            Log.d(TAG, "MAIN: ")
        }
        Log.d(TAG, "updateData: ")
    }

    fun getBasicCoroutine(start: CoroutineStart, scope: CoroutineScope): Job {
        return scope.launch() {

            Log.d("MAIN_AC", "Job start ")
            delay(5000L)
        }
    }

    fun something() = runBlocking {
        //1
        launch {
           //3
        }
        //2
        //dsdadasd
        //dasdasd
    }

    suspend fun something1() {
        //dsdadasd
        //dasdasd
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    val TAG = "d_Example"
}