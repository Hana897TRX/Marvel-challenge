package com.wizeline.heroes.data.remote

import com.wizeline.heroes.data.models.Characters
import com.wizeline.heroes.utils.DataStates
import kotlinx.coroutines.flow.Flow

interface HeroesRemoteDataSource {
    suspend fun getCharacters() : Flow<DataStates<Characters>>
}