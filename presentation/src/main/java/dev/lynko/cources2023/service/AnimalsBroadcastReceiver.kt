package dev.lynko.cources2023.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AnimalsBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("12345", "onReceive: ")
        //CallService.decline()
    }
}