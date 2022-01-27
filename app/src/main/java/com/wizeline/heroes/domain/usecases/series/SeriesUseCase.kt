package com.wizeline.heroes.domain.usecases.series

import com.wizeline.heroes.data.models.model.series.SeriesModel
import com.wizeline.heroes.utils.DataStates
import kotlinx.coroutines.flow.Flow

interface SeriesUseCase {
    suspend fun getSeries(characterId : String) : Flow<DataStates<SeriesModel>>
}