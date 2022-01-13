package com.wizeline.heroes.utils


sealed class DataStates<out T : Any?> {
    data class Success<out T : Any?>(val data : T) : DataStates<T>()
    object Loading : DataStates<Nothing>()
    data class Error(val code : Int = 0, val errorMessage : String = "") : DataStates<Nothing>()
}