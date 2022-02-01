package com.wizeline.heroes.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.heroes.data.models.model.home.DataModel
import com.wizeline.heroes.domain.usecases.search.SearchUseCase
import com.wizeline.heroes.utils.DataStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
): ViewModel() {

    private var _characters : MutableStateFlow<DataStates<DataModel>> = MutableStateFlow(DataStates.Loading)
    val characters : StateFlow<DataStates<DataModel>> = _characters

    private var _searchInput : MutableStateFlow<String> = MutableStateFlow("")

    init {
        getCharacters()
    }

    fun searchText(searchInput : String) = viewModelScope.launch {
        _searchInput.emit(searchInput)
        _characters.emit(DataStates.Loading)
        getCharacters()
    }

    private fun getCharacters() = viewModelScope.launch {
        _searchInput.debounce(500).collect { character ->
            searchUseCase.searchCharacters(character, 0).collect { response ->
                when(response) {
                    is DataStates.Error -> _characters.emit(DataStates.Error(response.code, response.errorMessage))
                    is DataStates.Loading -> _characters.emit(DataStates.Loading)
                    is DataStates.Success -> _characters.emit(DataStates.Success(response.data))
                }
            }
        }
    }
}