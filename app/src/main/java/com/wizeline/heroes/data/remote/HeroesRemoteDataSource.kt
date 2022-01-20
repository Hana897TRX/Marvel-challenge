package com.wizeline.heroes.data.remote

import com.wizeline.heroes.data.models.CharacterModel
import com.wizeline.heroes.utils.DataStates
import kotlinx.coroutines.flow.Flow

interface HeroesRemoteDataSource {
    suspend fun getCharacters(offset : Int) : Flow<DataStates<CharacterModel>>
}