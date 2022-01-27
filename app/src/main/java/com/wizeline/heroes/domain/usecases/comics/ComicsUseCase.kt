package com.wizeline.heroes.domain.usecases.comics

import com.wizeline.heroes.data.models.model.series.SeriesModel
import com.wizeline.heroes.utils.DataStates
import kotlinx.coroutines.flow.Flow

interface ComicsUseCase {
    suspend fun getComics(characterId : String) : Flow<DataStates<SeriesModel>>
}