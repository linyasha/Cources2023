package dev.lynko.cources2023

import android.app.Application
import dev.lynko.cources2023.database.AppDatabase

class MyAnimalsApp: Application() {

    val database by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: MyAnimalsApp
    }

}