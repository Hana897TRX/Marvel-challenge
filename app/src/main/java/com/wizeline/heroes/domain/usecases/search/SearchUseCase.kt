package com.wizeline.heroes.domain.usecases.search

import com.wizeline.heroes.data.models.model.home.DataModel
import com.wizeline.heroes.utils.DataStates
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {
    suspend fun searchCharacters(nameStartsWith : String?, offset: Int) : Flow<DataStates<DataModel>>
}