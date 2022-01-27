package com.wizeline.heroes.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.heroes.data.models.model.home.ResultModel
import com.wizeline.heroes.data.models.model.series.SeriesModel
import com.wizeline.heroes.domain.usecases.comics.ComicsUseCase
import com.wizeline.heroes.domain.usecases.series.SeriesUseCase
import com.wizeline.heroes.utils.DataStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailFragmentViewModel @Inject constructor(
    private val comicsUseCase: ComicsUseCase,
    private val seriesUseCase: SeriesUseCase
) : ViewModel() {
    private var _comicsUIState: MutableStateFlow<DataStates<SeriesModel>> =
        MutableStateFlow(DataStates.Loading)
    val comicsUiState: StateFlow<DataStates<SeriesModel>> = _comicsUIState

    private var _seriesUIState: MutableStateFlow<DataStates<SeriesModel>> =
        MutableStateFlow(DataStates.Loading)
    val seriesUiState: StateFlow<DataStates<SeriesModel>> = _seriesUIState

    var character: ResultModel? = null

    fun getData() {
        character?.let {
            getComics(it.id.toString())
            getSeries(it.id.toString())
        }
    }

    private fun getComics(characterId: String) = viewModelScope.launch {
        comicsUseCase.getComics(characterId).collect { response ->
            when (response) {
                is DataStates.Success -> _comicsUIState.emit(DataStates.Success(response.data))
                is DataStates.Loading -> _comicsUIState.emit(DataStates.Loading)
                is DataStates.Error -> _comicsUIState.emit(
                    DataStates.Error(
                        response.code,
                        response.errorMessage
                    )
                )
            }
        }
    }

    private fun getSeries(characterId: String) = viewModelScope.launch {
        seriesUseCase.getSeries(characterId).collect { response ->
            when (response) {
                is DataStates.Success -> _seriesUIState.emit(DataStates.Success(response.data))
                is DataStates.Loading -> _seriesUIState.emit(DataStates.Loading)
                is DataStates.Error -> _seriesUIState.emit(
                    DataStates.Error(
                        response.code,
                        response.errorMessage
                    )
                )
            }
        }
    }
}