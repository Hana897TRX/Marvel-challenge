package com.wizeline.heroes.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.heroes.data.models.Characters
import com.wizeline.heroes.domain.usecases.HeroesUseCase
import com.wizeline.heroes.utils.DataStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val heroesUseCase: HeroesUseCase
): ViewModel() {
    private var _heroesUIState : MutableStateFlow<DataStates<Characters>> = MutableStateFlow(DataStates.Loading)
    var heroesUIState : StateFlow<DataStates<Characters>> = _heroesUIState

    init {
        getCharacters()
    }

    private fun getCharacters() = viewModelScope.launch(Dispatchers.IO) {
        heroesUseCase().collect { response ->
            when(response) {
                is DataStates.Success -> _heroesUIState.emit(DataStates.Success(response.data))
                is DataStates.Error -> _heroesUIState.emit(DataStates.Error(response.code, response.errorMessage))
                is DataStates.Loading -> _heroesUIState.emit(DataStates.Loading)
            }
        }
    }
}