package dev.lynko.cources2023.repository

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import dev.lynko.cources2023.AnimalsViewModel
import dev.lynko.cources2023.MyAnimalsApp
import dev.lynko.cources2023.databinding.FragmentAddAnimalBinding
import dev.lynko.cources2023.model.Animal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

var animalsRepository: AnimalsRepository =  AnimalsRepository(MyAnimalsApp.INSTANCE.database.animalsDao())
class AddAnimalFragment : Fragment() {
    private lateinit var binding: FragmentAddAnimalBinding
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var animalAdapter: AnimalAdapter
    private lateinit var viewModel: AnimalsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddAnimalBinding.inflate(inflater, container, false)
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
                    val type = when (spinner.selectedItemPosition) {
                        0 -> Animal.TYPE_DOG
                        1 -> Animal.TYPE_CAT
                        2 -> Animal.TYPE_HAMSTER
                        3 -> Animal.TYPE_FISH
                        4 -> Animal.TYPE_BIRD
                        else -> Animal.TYPE_DOG
                    }
                    val description = breedEditText.run {
                        val textVar = text.toString()
                        text?.clear()
                        textVar.toString()
                    }
//              TODO("Поменяйте на lifecycleScope")
                    kotlinx.coroutines.GlobalScope.launch(kotlinx.coroutines.Dispatchers.IO) {
                        animalsRepository.insertAnimal(Animal(type, name, age, weight,description))
                    }

                }
            //TODO("Поменяйте на lifecycleScope")
            lifecycleScope.launch(Dispatchers.IO) {
                val animals = animalsRepository.getAllAnimals()
                withContext(kotlinx.coroutines.Dispatchers.Main) {
                    //TODO(Список откравляется в adapter RecyclerView(как в примере, который мы рассматривали,
                    // когда изучали его))
                    binding.setText(animals.toString())
                }
            }
            }}

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddAnimalFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

