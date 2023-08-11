package dev.lynko.cources2023

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.*

class MyForegroundService: Service() {

    val serviceScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    companion object {
        const val TAG = "MyForegroundService"
    }
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: MyForegroundService")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: MyForegroundService")
        doWork(intent?.getStringExtra("KEY") ?: "")
        val notification = NotificationCompat.Builder(this, "my_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("My foreground started")
            .setContentText("50 sec")
            .build()
        startForeground(
        1,
            notification
        )
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
        super.onDestroy()
        Log.d(TAG, "onDestroy: MyForegroundService")
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}