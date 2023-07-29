package dev.lynko.cources2023

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.Toolbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val appName = getString(R.string.app_name)
        val someString = getString(R.string.some_string)
        Log.d("MAIN_ACT", "onCreate: $appName ")
        Log.d("MAIN_ACT", "onCreate: $someString ")
        Log.d("MAIN_ACT", "onCreate: ${VersionController.printVersion()} ")

    }

}