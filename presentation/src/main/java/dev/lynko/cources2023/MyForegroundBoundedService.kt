package dev.lynko.cources2023

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class MyForegroundBoundedService: Service() {

    val binder = MyBinder()
    
    val serviceScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    companion object {
        const val TAG = "MyForegroundBoundedService"
    }
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: MyForegroundBoundedService")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: MyForegroundBoundedService")
        return START_NOT_STICKY
    }

    private fun doWork(count: String) {
        serviceScope.launch(Dispatchers.IO) {
            while (true) {
                delay(1000L)

                Log.d(TAG, "doWork: $count ")
            }
        }
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: MyForegroundBoundedService")
        super.onDestroy()
        serviceScope.cancel()

    }

    override fun onBind(p0: Intent?): IBinder? {
        Log.d(TAG, "onBind MyForegroundBoundedService: ")
        return binder
    }

    inner class MyBinder(): Binder() {
        fun startWork() {
            doWork("Hello")
        }
    }

}