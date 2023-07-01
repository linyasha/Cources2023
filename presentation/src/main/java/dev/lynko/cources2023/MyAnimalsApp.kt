package dev.lynko.cources2023

import android.app.Application
import dev.lynko.cources2023.di.dataModule
import dev.lynko.cources2023.di.domainModule
import dev.lynko.cources2023.di.presentationModule
import dev.lynko.data.database.AppDatabase
import org.koin.core.context.startKoin


class MyAnimalsApp: Application() {

    val database by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(dataModule, presentationModule, domainModule)
        }
        INSTANCE = this

    }

    companion object {
        lateinit var INSTANCE: MyAnimalsApp
    }

}