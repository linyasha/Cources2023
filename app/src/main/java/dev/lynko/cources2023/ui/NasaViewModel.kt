package dev.lynko.cources2023.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.lynko.cources2023.ApiResult
import dev.lynko.cources2023.NasaApiImpl
import dev.lynko.cources2023.utils.LoadingState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NasaViewModel: ViewModel() {

    private var _state: MutableStateFlow<LoadingState> = MutableStateFlow(LoadingState.DEFAULT)
    val state: StateFlow<LoadingState> = _state

    suspend fun getData(): ApiResult? {
        _state.emit(LoadingState.LOAGING)
        val result = NasaApiImpl.getPlanetary()
        return result?.let {
            _state.emit(LoadingState.SUCCESS)
            it

        } ?: run {
            _state.emit(LoadingState.FAIL)
            null
        }
    }
}

class NasaViewModelProviderFactory(): ViewModelProvider.Factory{
    override fun <T:ViewModel> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(NasaViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return NasaViewModel() as T
        }
        throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
    }
}