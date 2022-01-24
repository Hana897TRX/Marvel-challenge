package com.wizeline.heroes.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.heroes.data.models.model.home.CharacterModel
import com.wizeline.heroes.domain.usecases.HeroesUseCase
import com.wizeline.heroes.utils.DataStates
import com.wizeline.heroes.utils.Network.OFFSET_CONFIG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val heroesUseCase: HeroesUseCase
) : ViewModel() {
    private var _heroesUIState: MutableStateFlow<DataStates<CharacterModel>> = MutableStateFlow(DataStates.Loading)
    private var _offset: MutableStateFlow<Int> = MutableStateFlow(0)

    var heroesUIState: StateFlow<DataStates<CharacterModel>> = _heroesUIState
    val offset: StateFlow<Int> = _offset

    init {
        getCharacters(_offset.value)
    }

    fun previousPage() = viewModelScope.launch {
        if (_offset.value - OFFSET_CONFIG >= 0)
            _offset.emit(_offset.value - OFFSET_CONFIG)
        else
            _offset.emit(0)
    }

    fun nextPage() = viewModelScope.launch {
        _offset.emit(_offset.value + OFFSET_CONFIG)
    }

    fun setCustomOffset(number: CharSequence?) = viewModelScope.launch {
        try {
            if (!number.isNullOrEmpty()) {
                number.toString().toInt().run {
                    if (_offset.value != this) {
                        _offset.emit(this)
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("OFFSET", e.message ?: "")
        }
    }

    fun getCharacters(offset: Int) = viewModelScope.launch {
        heroesUseCase(offset).collect { response ->
            when (response) {
                is DataStates.Success -> _heroesUIState.emit(DataStates.Success(response.data))
                is DataStates.Error -> _heroesUIState.emit(
                    DataStates.Error(
                        response.code,
                        response.errorMessage
                    )
                )
                is DataStates.Loading -> _heroesUIState.emit(DataStates.Loading)
            }
        }
    }
}