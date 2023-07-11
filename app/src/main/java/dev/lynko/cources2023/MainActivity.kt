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

class MainActivity : AppCompatActivity(), AnimalsFragment.onAddAnimal {

    private lateinit var binding: ActivityMainBinding

    private lateinit var animalsRepository: AnimalsRepository

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
        animalsRepository = AnimalsRepository(MyAnimalsApp.INSTANCE.database.animalsDao())

        with(binding) {
            //TODO(Перенесите в AddAnimalFragment)
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
                GlobalScope.launch(Dispatchers.IO) {
                    animalsRepository.insertAnimal(Animal(type, name, age, weight, "Good", "sdfsdfgsd"))
                }

            }
            GlobalScope.launch(Dispatchers.IO) {
                val animals = animalsRepository.getAllAnimas()
                withContext(Dispatchers.Main) {
                    //TODO(Список откравляется в adapter RecyclerView(как в примере, который мы рассматривали,
                    // когда изучали его))
                    binding.result.setText(animals.toString())
                }
            }

        }
    }

    fun transactionToAddAnimal() {
//                supportFragmentManager.beginTransaction()
    }

    override fun addAnimal() {
//        supportFragmentManager.beginTransaction()
    }
}