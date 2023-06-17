package dev.lynko.cources2023

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.Toolbar
import dev.lynko.cources2023.databinding.ActivityMainBinding
import dev.lynko.cources2023.model.Animal
import dev.lynko.cources2023.repository.AnimalsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var animalsRepository: AnimalsRepository

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        animalsRepository = AnimalsRepository(MyAnimalsApp.INSTANCE.database.animalsDao())


        with(binding) {
            add.setOnClickListener {
                val name = nameEditText.run {
                    val textVar = text.toString()
                    text?.clear()
                    textVar
                }

                val age = ageEditText.run {
                    val textVar = text.toString()
                    text?.clear()
                    textVar.toInt()
                }
                val weight = weightEditText.run {
                    val textVar = text.toString()
                    text?.clear()
                    textVar.toDouble()
                }
                val type = Animal.TYPE_CAT
//                TODO("Change to lifecycleScope")
                GlobalScope.launch(Dispatchers.IO) {
                    animalsRepository.insertAnimal(Animal(type, name, age, weight))
                }

            }
            get.setOnClickListener {
                //TODO("Change to lifecycleScope")
                GlobalScope.launch(Dispatchers.IO) {
                    val animals = animalsRepository.getAllAnimas()
                    withContext(Dispatchers.Main) {
                        binding.result.setText(animals.toString())
                    }
                }
            }
        }


    }

}