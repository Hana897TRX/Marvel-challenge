package com.wizeline.heroes.domain.repository

import com.wizeline.heroes.data.models.Characters
import com.wizeline.heroes.utils.DataStates
import kotlinx.coroutines.flow.Flow

interface HeroesRepository {
    suspend fun getCharacters() : Flow<DataStates<Characters>>
}