package com.wizeline.heroes.domain.repository.series

import com.wizeline.heroes.data.models.model.series.SeriesModel
import com.wizeline.heroes.utils.DataStates
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {
    suspend fun getSeries(characterId : String) : Flow<DataStates<SeriesModel>>
}