package dev.lynko.cources2023

import android.app.Application
import dev.lynko.cources2023.di.AppComponent
import dev.lynko.cources2023.di.DaggerAppComponent
import dev.lynko.cources2023.di.PresentationModule
import dev.lynko.data.database.AppDatabase


class MyAnimalsApp: Application() {

    val database by lazy { AppDatabase.getDatabase(this) }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        appComponent =
            DaggerAppComponent.builder()
                .presentationModule(PresentationModule(context = this))
                .build()
    }

    companion object {
        lateinit var INSTANCE: MyAnimalsApp
    }

}