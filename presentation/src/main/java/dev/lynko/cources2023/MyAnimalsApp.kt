package dev.lynko.cources2023

import android.app.Application
import dev.lynko.cources2023.di.dataModule
import dev.lynko.cources2023.di.domainModule
import dev.lynko.cources2023.di.presentationModule
import dev.lynko.data.local.database.AppDatabase
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

    }

    companion object {
        lateinit var INSTANCE: MyAnimalsApp
    }

}