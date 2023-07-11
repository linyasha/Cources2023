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
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnimalsViewModel (
    private val getAnimalUseCase: GetAllAnimalsUseCase,
    private val getFlowAnimalUseCase: GetFlowAnimalsUseCase,
    private val insertAnimalUseCase: InsertAnimalUseCase
    ): ViewModel() {

    private val _state: MutableStateFlow<ValidateState> = MutableStateFlow(ValidateState.DEFAULT)
    val state: StateFlow<ValidateState> = _state


    val animals: SharedFlow<List<Animal>> = getFlowAnimalUseCase.execute().shareIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        replay = 1
    )

    //  1 4 6 12 64 34

//    sub1 (1, 4, 6, 12, 64, 34)
                        //sub 2 (4, 6, 12, 64, 34)
                                                //    sub3(12 64 34)

//    val animals: StateFlow<List<Animal>> = getFlowAnimalUseCase.execute().stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.Eagerly,
//        listOf()
//    )


       /* .stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = listOf()
    )*/

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


class AnimalsViewModelFactory @Inject constructor(
    private val getAnimalUseCase: GetAllAnimalsUseCase,
    private val getFlowAnimalUseCase: GetFlowAnimalsUseCase,
    private val insertAnimalUseCase: InsertAnimalUseCase
    ): ViewModelProvider.Factory{
    override fun <T:ViewModel> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(AnimalsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return AnimalsViewModel(
                getAnimalUseCase,
                getFlowAnimalUseCase,
                insertAnimalUseCase
            ) as T
        }
        throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
    }
}