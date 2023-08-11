package dev.lynko.cources2023

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import dev.lynko.cources2023.di.dataModule
import dev.lynko.cources2023.di.domainModule
import dev.lynko.cources2023.di.presentationModule
import dev.lynko.data.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class MyAnimalsApp: Application() {

    val database by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyAnimalsApp)
            androidLogger(Level.INFO)
            modules(dataModule, presentationModule, domainModule)
        }
        INSTANCE = this
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "my_channel",
                "Animals",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "Some information from firebase"
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }

    companion object {
        lateinit var INSTANCE: MyAnimalsApp
    }

}