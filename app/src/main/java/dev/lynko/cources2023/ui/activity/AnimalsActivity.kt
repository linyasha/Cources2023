package dev.lynko.cources2023.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import dev.lynko.cources2023.MyAnimalsApp
import dev.lynko.cources2023.databinding.ActivityMainBinding
import dev.lynko.cources2023.model.Animal
import dev.lynko.cources2023.model.ValidateState
import dev.lynko.cources2023.repository.AnimalsRepository
import dev.lynko.cources2023.ui.activity.viewModel.AnimalsViewModel
import dev.lynko.cources2023.ui.activity.viewModel.AnimalsViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimalsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: AnimalsViewModel

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
        viewModel = ViewModelProvider(this, AnimalsViewModelFactory(
            AnimalsRepository(MyAnimalsApp.INSTANCE.database.animalsDao())
        )).get(AnimalsViewModel::class.java)
        viewModel.animals.observe(this) { animals ->
            Log.d("HAHAH", "observe: $animals ")
            binding.result.setText(animals.toString())
        }
        viewModel.state.observe(this) { status ->
            with(binding) {
                when(status) {
                    ValidateState.SUCCESS -> {
                        nameEditText.setText("")
                        ageEditText.setText("")
                        weightEditText.setText("")
                    }
                    ValidateState.FAIL -> {
                        Toast.makeText(this@AnimalsActivity, "Check data!", Toast.LENGTH_SHORT).show()
                    }
                    ValidateState.DEFAULT -> {}
                }
            }
        }
        with(binding) {
            //TODO(Перенесите в AddAnimalFragment)
            add.setOnClickListener {
                viewModel.insertAnimal(
                    nameEditText.text.toString(),
                    ageEditText.text.toString(),
                    weightEditText.text.toString(),
                    Animal.TYPE_CAT
                )
            }
            //TODO("Поменяйте на lifecycleScope")
//            GlobalScope.launch(Dispatchers.IO) {
////                val animals = animalsRepository.getAllAnimas()
//                withContext(Dispatchers.Main) {
//                    //TODO(Список откравляется в adapter RecyclerView(как в примере, который мы рассматривали,
//                    // когда изучали его))
////                    binding.result.setText(animals.toString())
//                }
//            }

        }
    }
}
