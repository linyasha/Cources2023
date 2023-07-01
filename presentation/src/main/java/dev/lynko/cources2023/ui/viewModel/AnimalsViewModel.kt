package dev.lynko.cources2023.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.lynko.cources2023.ui.model.ValidateState
import dev.lynko.domain.models.Animal
import dev.lynko.domain.repository.AnimalsRepository
import dev.lynko.domain.usecases.GetAllAnimalsUseCase
import dev.lynko.domain.usecases.GetFlowAnimalsUseCase
import dev.lynko.domain.usecases.InsertAnimalUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AnimalsViewModel(
    private val getAnimalUseCase: GetAllAnimalsUseCase,
    private val getFlowAnimalUseCase: GetFlowAnimalsUseCase,
    private val insertAnimalUseCase: InsertAnimalUseCase,
    private val accountId: Int
    ): ViewModel() {

    private val _state: MutableLiveData<ValidateState> = MutableLiveData(ValidateState.DEFAULT)
    val state: LiveData<ValidateState> = _state

    val animals: StateFlow<List<Animal>> = getFlowAnimalUseCase.execute().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = listOf()
    )

    fun insertAnimal(name: String, age: String, weight: String, type: Byte) {
        val animal = isDataValid(name, age, weight, type) ?: return
        viewModelScope.launch(Dispatchers.IO) {
            insertAnimalUseCase.execute(animal)
        }
    }

    fun getAllAnimals() {
        viewModelScope.launch {
            val result = getAnimalUseCase.execute()
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