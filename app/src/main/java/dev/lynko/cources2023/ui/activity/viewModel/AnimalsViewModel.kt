package dev.lynko.cources2023.ui.activity.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.lynko.cources2023.model.Animal
import dev.lynko.cources2023.model.ValidateState
import dev.lynko.cources2023.repository.AnimalsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AnimalsViewModel(private val repository: AnimalsRepository): ViewModel() {

    private val _state: MutableLiveData<ValidateState> = MutableLiveData(ValidateState.DEFAULT)
    val state: LiveData<ValidateState> = _state

    val animals: LiveData<List<Animal>> = repository.getAllAnimasLiveData()

    fun insertAnimal(name: String, age: String, weight: String, type: Byte) {
        val animal = isDataValid(name, age, weight, type) ?: return
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertAnimal(animal)
        }
    }

    private fun isDataValid(name: String, age: String, weight: String, type: Byte): Animal? {
        return try {
            val ageInt = age.toInt()
            val weightDouble = weight.toDouble()
            Animal(
                name = name,
                age = ageInt,
                weight = weightDouble,
                type = type
            ).also {
                _state.value = ValidateState.SUCCESS
            }
        } catch (e: Exception) {
            _state.value = ValidateState.FAIL
            null
        }
    }
}

class AnimalsViewModelFactory(private val repository: AnimalsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimalsViewModel::class.java)) {
            return AnimalsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown viewModel!")
    }
}