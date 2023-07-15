package dev.lynko.cources2023.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lynko.cources2023.ui.model.ValidateState
import dev.lynko.domain.models.Animal
import dev.lynko.domain.models.Status
import dev.lynko.domain.models.UserCreds
import dev.lynko.domain.usecases.animals.GetAllAnimalsUseCase
import dev.lynko.domain.usecases.animals.GetFlowAnimalsUseCase
import dev.lynko.domain.usecases.animals.InsertAnimalUseCase
import dev.lynko.domain.usecases.user.LoginUserUseCase
import dev.lynko.domain.usecases.user.RegistrationUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUserUseCase: LoginUserUseCase,
    private val registrationUserUseCase: RegistrationUserUseCase
    ): ViewModel() {

    private val _state: MutableLiveData<ValidateState> = MutableLiveData(ValidateState.DEFAULT)
    val state: LiveData<ValidateState> = _state

    private val _authState: MutableSharedFlow<Status> = MutableSharedFlow(
        replay = 1
    )
    val authState: SharedFlow<Status> = _authState

    fun login(email: String, password: String) {
        val userCreds = isDataValid(email, password) ?: return
        viewModelScope.launch(Dispatchers.IO) {
            _authState.emit(loginUserUseCase.execute(userCreds))
        }
    }

    fun signUp(email: String, password: String) {
        val userCreds = isDataValid(email, password) ?: return
        viewModelScope.launch(Dispatchers.IO) {
            _authState.emit(registrationUserUseCase.execute(userCreds))
        }
    }

    private fun isDataValid(email: String, password: String): UserCreds? {
        return try {
            //TODO("Check regex")
            UserCreds(
                email = email,
                password = password
            ).also {
                _state.value = ValidateState.SUCCESS
            }
        } catch (e: Exception) {
            _state.value = ValidateState.FAIL
            null
        }
    }
}