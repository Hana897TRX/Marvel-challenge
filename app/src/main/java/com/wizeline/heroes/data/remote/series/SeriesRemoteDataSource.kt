package com.wizeline.heroes.data.remote.series

import com.wizeline.heroes.data.models.model.detail.CharacterSeriesModel
import com.wizeline.heroes.data.models.model.series.SeriesModel
import com.wizeline.heroes.utils.DataStates
import kotlinx.coroutines.flow.Flow

interface SeriesRemoteDataSource {
    suspend fun getSeries(characterId : String) : Flow<DataStates<SeriesModel>>
}