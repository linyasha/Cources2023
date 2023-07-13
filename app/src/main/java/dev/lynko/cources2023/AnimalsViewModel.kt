package dev.lynko.cources2023

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.lynko.cources2023.model.Animal
import dev.lynko.cources2023.model.ValidateState
import dev.lynko.cources2023.repository.AnimalsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimalsViewModel(private val repository: AnimalsRepository): ViewModel() {

    private val _state: MutableLiveData<ValidateState> = MutableLiveData(ValidateState.DEFAULT)
    val state: LiveData<ValidateState> = _state

    val animals: LiveData<List<Animal>> = repository.getAllAnimasLiveData()

    fun insertAnimal(name: String, age: String, weight: String, type: Byte, createdAt: Long, description: String) {
        val animal = isDataValid(name, age, weight, type,createdAt, description ) ?: return
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertAnimal(animal)
        }
    }

    private fun isDataValid(name: String, age: String, weight: String, type: Byte,createdAt: Long,description: String): Animal? {
        return try {
            val ageInt = age.toInt()
            val weightDouble = weight.toDouble()
            val createdAt = System.currentTimeMillis()
            val description =description.toString()
            Animal(
                name = name,
                age = ageInt,
                weight = weightDouble,
                type = type,
                createdAt = createdAt,
                description = description



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