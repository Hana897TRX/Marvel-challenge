package com.wizeline.heroes.ui.home

import android.annotation.SuppressLint
import android.os.StrictMode
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.heroes.data.models.model.home.DataModel
import com.wizeline.heroes.domain.usecases.heroes.HeroesUseCase
import com.wizeline.heroes.domain.usecases.heroes.HeroesUseCaseRx
import com.wizeline.heroes.utils.ConstVals.EMPTY_VALUE
import com.wizeline.heroes.utils.DataStates
import com.wizeline.heroes.utils.Network.OFFSET_CONFIG
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val heroesUseCase: HeroesUseCaseRx
) : ViewModel() {
    private var _heroesUIState: MutableStateFlow<DataStates<DataModel>> =
        MutableStateFlow(DataStates.Loading)
    private var _offset: MutableStateFlow<Int> = MutableStateFlow(0)

    var heroesUIState: StateFlow<DataStates<DataModel>> = _heroesUIState
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

    @SuppressLint("CheckResult")
    fun getCharacters(offset: Int) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        heroesUseCase.invoke(offset)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        return@map DataStates.Success(it.data)
                    } ?: DataStates.Error(response.code(), response.message())
                } else {
                    DataStates.Error(response.code(), response.message())
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                when (response) {
                    is DataStates.Success -> _heroesUIState.value =
                        DataStates.Success(response.data)
                    is DataStates.Error -> _heroesUIState.value =
                        DataStates.Error(
                            response.code,
                            response.errorMessage
                        )
                    is DataStates.Loading -> _heroesUIState.value = DataStates.Loading
                }
            }, {
                _heroesUIState.value =
                    DataStates.Error(
                        0,
                        it.cause?.message ?: EMPTY_VALUE
                    )
            })
    }
}