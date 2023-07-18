package dev.lynko.cources2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import dev.lynko.cources2023.databinding.ActivityNasaBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NasaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNasaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNasaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        GlobalScope.launch(Dispatchers.Main) {
            val resultUri = NasaApiImpl.getPlanetary()
            Glide.with(this@NasaActivity)
                .load(resultUri)
                .into(binding.result)
        }
    }
}