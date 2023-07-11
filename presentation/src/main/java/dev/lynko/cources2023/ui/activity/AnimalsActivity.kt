package dev.lynko.cources2023.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dev.lynko.cources2023.MyAnimalsApp
import dev.lynko.cources2023.databinding.ActivityMainBinding
import dev.lynko.cources2023.ui.model.ValidateState

import dev.lynko.cources2023.ui.viewModel.AnimalsViewModel
import dev.lynko.domain.models.Animal

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.compat.ViewModelCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf


class AnimalsActivity : AppCompatActivity(), KoinComponent {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: AnimalsViewModel by viewModel()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {
            viewModel.animals.collectLatest { animals ->
                Log.d("HAHAH", "observe: $animals ")
                binding.result.setText(animals.toString())
            }
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
            add.setOnClickListener {
                viewModel.insertAnimal(
                    nameEditText.text.toString(),
                    ageEditText.text.toString(),
                    weightEditText.text.toString(),
                    Animal.TYPE_CAT
                )
            }
        }
    }
}
