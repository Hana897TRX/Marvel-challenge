package com.wizeline.heroes.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.heroes.data.models.CharacterModel
import com.wizeline.heroes.domain.usecases.HeroesUseCase
import com.wizeline.heroes.utils.DataStates
import com.wizeline.heroes.utils.Network.OFFSET_CONFIG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val heroesUseCase: HeroesUseCase
): ViewModel() {
    private var _heroesUIState : MutableStateFlow<DataStates<CharacterModel>> = MutableStateFlow(DataStates.Loading)
    private var _offset : MutableLiveData<Int> = MutableLiveData(0)
    val offset : LiveData<Int> = _offset
    var heroesUIState : StateFlow<DataStates<CharacterModel>> = _heroesUIState

    init {
        getCharacters(_offset.value ?: 0)
    }

    fun previousPage() {
        _offset.value?.let {
            if(it - OFFSET_CONFIG >= 0) {
                _offset.value = it - OFFSET_CONFIG
            }
            else {
                _offset.value = 0
            }
        }
    }

    fun nextPage() = _offset.value?.let {
        _offset.value = it + OFFSET_CONFIG
    }

    fun setCustomOffset(number: CharSequence?) {
        try {
            if (!number.isNullOrEmpty()) {
                number.toString().toInt().run {
                    if (_offset.value != this) {
                        _offset.value = this
                    }
                }
            }
        }
        catch (e : Exception) {
            Log.e("OFFSET", e.message ?: "")
        }
    }

    fun getCharacters(offset : Int) = viewModelScope.launch(Dispatchers.IO) {
        heroesUseCase(offset).collect { response ->
            when(response) {
                is DataStates.Success -> _heroesUIState.emit(DataStates.Success(response.data))
                is DataStates.Error -> _heroesUIState.emit(DataStates.Error(response.code, response.errorMessage))
                is DataStates.Loading -> _heroesUIState.emit(DataStates.Loading)
            }
        }
    }
}