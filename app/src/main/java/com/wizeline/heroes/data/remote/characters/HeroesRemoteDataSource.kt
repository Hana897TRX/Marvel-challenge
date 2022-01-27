package com.wizeline.heroes.data.remote.characters

import com.wizeline.heroes.data.models.model.home.DataModel
import com.wizeline.heroes.data.models.model.series.SeriesModel
import com.wizeline.heroes.utils.DataStates
import kotlinx.coroutines.flow.Flow

interface HeroesRemoteDataSource {
    suspend fun getCharacters(offset : Int) : Flow<DataStates<DataModel>>
}