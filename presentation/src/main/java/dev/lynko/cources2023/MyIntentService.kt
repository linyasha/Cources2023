package dev.lynko.cources2023

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyIntentService: IntentService("My intent service") {

    companion object {
        const val TAG = "MyIntentService"

        private var instanse: MyIntentService? = null

        fun stopService() {
            instanse?.stopSelf()
            instanse = null
        }
    }

    init {
        instanse = this
        Log.d(TAG, "MyIntentService init")
    }

    override fun onHandleIntent(p0: Intent?) {
        val data = p0?.getStringExtra("KEY")
        Log.d(TAG, "MyIntentService onHandleIntent: $data ")
        while (true) {
            Log.d(TAG, "MyIntentService working...")
            Thread.sleep(1000L)
        }
        Log.d(TAG, "MyIntentService onHandleIntent finished")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "MyIntentService onDestroy")
    }

}