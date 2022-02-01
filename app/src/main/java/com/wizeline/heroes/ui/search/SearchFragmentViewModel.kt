package com.wizeline.heroes.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.heroes.data.models.model.home.DataModel
import com.wizeline.heroes.domain.usecases.search.SearchUseCase
import com.wizeline.heroes.utils.ConstVals.EMPTY_VALUE
import com.wizeline.heroes.utils.DataStates
import com.wizeline.heroes.utils.Network
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
): ViewModel() {

    private var _characters : MutableStateFlow<DataStates<DataModel>> = MutableStateFlow(DataStates.Loading)
    private var _searchInput : MutableStateFlow<String> = MutableStateFlow("")
    private var _offset: MutableStateFlow<Int> = MutableStateFlow(0)

    val characters : StateFlow<DataStates<DataModel>> = _characters
    val offset: StateFlow<Int> = _offset

    init {
        getCharacters()
    }

    fun searchText(searchInput : String) = viewModelScope.launch {
        if(searchInput != _searchInput.value) {
            _searchInput.emit(searchInput)
            _characters.emit(DataStates.Loading)
            getCharacters()
        }
    }

    fun previousPage() = viewModelScope.launch {
        if (_offset.value - Network.OFFSET_CONFIG >= 0)
            _offset.emit(_offset.value - Network.OFFSET_CONFIG)
        else
            _offset.emit(0)
        getCharacters()
    }

    fun nextPage() = viewModelScope.launch {
        _offset.emit(_offset.value + Network.OFFSET_CONFIG)
        getCharacters()
    }

    fun setCustomOffset(number: CharSequence?) = viewModelScope.launch {
        try {
            if (!number.isNullOrEmpty()) {
                number.toString().toInt().run {
                    if (_offset.value != this) {
                        _offset.emit(this)
                        getCharacters()
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("OFFSET", e.message ?: EMPTY_VALUE)
        }
    }

    private fun getCharacters() = viewModelScope.launch {
        _searchInput.zip(_offset) { input, offset  -> Pair(input, offset)}.debounce(350).collect { pair ->
            searchUseCase.searchCharacters(pair.first, pair.second).collect { response ->
                when(response) {
                    is DataStates.Error -> _characters.emit(DataStates.Error(response.code, response.errorMessage))
                    is DataStates.Loading -> _characters.emit(DataStates.Loading)
                    is DataStates.Success -> _characters.emit(DataStates.Success(response.data))
                }
            }
        }
    }
}