package com.wizeline.heroes.data.remote.comics

import com.wizeline.heroes.data.models.model.series.SeriesModel
import com.wizeline.heroes.utils.DataStates
import kotlinx.coroutines.flow.Flow

interface ComicsRemoteDataSource {
    suspend fun getComics(characterId : String) : Flow<DataStates<SeriesModel>>
}