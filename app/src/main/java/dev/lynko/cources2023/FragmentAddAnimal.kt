package dev.lynko.cources2023

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.lifecycle.lifecycleScope
import dev.lynko.cources2023.databinding.FragmentAddAnimalBinding
import dev.lynko.cources2023.model.Animal
import dev.lynko.cources2023.repository.AnimalsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FragmentAddAnimal : Fragment(), PopupMenu.OnMenuItemClickListener {
    private lateinit var binding: FragmentAddAnimalBinding
    private var type: Byte = Animal.TYPE_ANDROID

    private val animalsRepository = AnimalsRepository(MyAnimalsApp.INSTANCE.database.animalsDao())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddAnimalBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            save.setOnClickListener {
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
                val description = descriptionEditText.run {
                    val textVar = text.toString()
                    text?.clear()
                    textVar
                }
                val createdAt = System.currentTimeMillis()
                lifecycleScope.launch(Dispatchers.IO) {
                    animalsRepository.insertAnimal(Animal(type, name, age, weight, description, createdAt ))
                }

            }
        }
    }

    fun showPopup(v: View) {
        val popup = PopupMenu(context, v)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.popup, popup.menu)
        popup.show()
    }

    fun showMenu(v: View) {
        PopupMenu(context, v).apply {
            setOnMenuItemClickListener(this@FragmentAddAnimal)
            inflate(R.menu.popup)
            show()
        }
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.cat -> {
                type = Animal.TYPE_CAT
            }
            R.id.dog -> {
                type = Animal.TYPE_DOG
            }
            R.id.android -> {
                type = Animal.TYPE_ANDROID
            }
        }
        binding.popup.text = item.title
        return true
    }
}