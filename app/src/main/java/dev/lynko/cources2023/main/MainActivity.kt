package dev.lynko.cources2023.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import dev.lynko.cources2023.databinding.ActivityMainBinding
import dev.lynko.cources2023.example.ExampleThread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.incButton.setOnClickListener {
            try {
                binding.counter.text = (binding.counter.text.toString().toInt() + 1).toString()
            } catch (e: Exception) {
                binding.counter.text = "final"
            }
        }

    }

    override fun onResume() {
        super.onResume()
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(Runnable {
            ExampleThread(4, applicationContext, binding.counter).doAction()
        }, 3000L)
    }

}