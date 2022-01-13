package com.wizeline.heroes.domain.repository

import com.wizeline.heroes.data.models.CharacterModel
import com.wizeline.heroes.utils.DataStates
import kotlinx.coroutines.flow.Flow

interface HeroesRepository {
    suspend fun getCharacters(offset : Int) : Flow<DataStates<CharacterModel>>
}