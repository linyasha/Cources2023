package dev.lynko.cources2023

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyService: Service() {

    companion object {
        const val TAG = "MyService"
    }
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: MyService")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: MyService")
        doWork(intent?.getStringExtra("KEY") ?: "")
        return START_NOT_STICKY
    }

    private fun doWork(count: String) {
        GlobalScope.launch(Dispatchers.IO) {
            while (true) {
                delay(1000L)
                Log.d(TAG, "onStartCommand: $count ")
            }
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

}