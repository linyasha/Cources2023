package dev.lynko.cources2023.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import dev.lynko.cources2023.R
import dev.lynko.cources2023.ui.activity.AnimalsActivity
import dev.lynko.domain.usecases.user.LoginUserUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AnimalsNotificationManager(
    private val context: Context
) {


    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(
        title: String,
        body: String
    ) {
        //TODO("FLAG_ACTIVITY_CLEAR_TASK разобрать, почему не работает")
        val animalsActivityIntent = Intent(context, AnimalsActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        val animalsBroadCastIntent = Intent(context, AnimalsBroadcastReceiver::class.java)
        val animalsActivityPendingIntent = PendingIntent.getActivity(
            context,
            10,
            animalsActivityIntent,
            FLAG_IMMUTABLE
        )
        val actionPendingIntent = PendingIntent.getBroadcast(
            context,
            11,
            animalsBroadCastIntent,
            FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(context, ANIMALS_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification_icon)
            .setContentTitle(title)
            .setContentText(body)
            .addAction(
                R.drawable.ic_notification_icon,
                "Accept",
                actionPendingIntent,
            )
            .setAutoCancel(true)
            .setContentIntent(animalsActivityPendingIntent)
            .build()
        notificationManager.notify(
            1,
            notification
        )
    }

    companion object {
        const val ANIMALS_CHANNEL_ID = "ANIMALS_CHANNEL_ID"
    }
}