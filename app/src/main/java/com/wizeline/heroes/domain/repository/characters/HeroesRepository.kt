package com.wizeline.heroes.domain.repository.characters

import com.wizeline.heroes.data.models.model.home.DataModel
import com.wizeline.heroes.data.models.model.home.MarvelResponse
import com.wizeline.heroes.utils.DataStates
import kotlinx.coroutines.flow.Flow

interface HeroesRepository {
    suspend fun getCharacters(offset : Int) : Flow<DataStates<DataModel>>
}