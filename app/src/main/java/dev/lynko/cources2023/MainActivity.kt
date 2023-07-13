package dev.lynko.cources2023

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dev.lynko.cources2023.databinding.ActivityMainBinding
import dev.lynko.cources2023.model.Animal
import dev.lynko.cources2023.model.ValidateState
import dev.lynko.cources2023.repository.AddAnimalFragment
import dev.lynko.cources2023.repository.AnimalsFragment
import dev.lynko.cources2023.repository.AnimalsRepository
import dev.lynko.cources2023.repository.animalsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    //TODO(Данное активити должно содержать два фрагмента, AnimalsFragment и AddAnimalFragment.
    // AnimalsFragment устанавливается по умолчанию, и содержит в себе RecyclerView, в котором будут
    // отображаться все добавленные питомцы(пока что будет достаточно просто при создании фрагмента
    // получать их их базы и заполнять recyclerView).
    // Так же AnimalsFragment содержит в себе две кнопки (пример их расположения будет на скриншоте),
    // по нажатию на Fab в нижнем правом углу должен открываться AddAnimalFragment(UI тоже будет на скриншоте),
    // в котором будут заполняться все необходимые данные и по нажатию на кнопку, сохраняться в базу данных)

    //TODO(Цвета и стили в UI необязательно делать как на скринах, проявите творчество))
    @SuppressLint("MissingInflatedId")


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       supportFragmentManager.beginTransaction()
           .add(R.id.container,AnimalsFragment.newInstance("",""))
           .commit()

//


        }
    }





