package dev.lynko.cources2023.service

import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MyFirebaseMessagingService(): FirebaseMessagingService() {

    private val animalsNotificationManager: AnimalsNotificationManager by inject()

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "onNewToken: ${token} ")

    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        animalsNotificationManager.showNotification(
            title = message.notification?.title ?: "",
            body = message.notification?.body ?: ""
        )
    }

    //dqHpSKXrRbG0f2w3n_Lfek:APA91bGyvx2YwkyKzCZQ2JjOjKnTbpjth3z1QqdSP8jFm-X_VAKoO4VrDMPsPnpeGa8xCq_WaWw_xPtRx68w648C4iCxWm0dimjFKcjrmbudyzogiS62ee_Kut1eBTy0OtsHju0UStO6

    companion object {
        const val TAG = "MyFirebaseMessagingService"
    }

}