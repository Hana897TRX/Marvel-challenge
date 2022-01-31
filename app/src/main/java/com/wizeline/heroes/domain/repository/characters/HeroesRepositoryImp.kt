package com.wizeline.heroes.domain.repository.characters

import com.wizeline.heroes.data.models.model.home.DataModel
import com.wizeline.heroes.data.models.model.home.MarvelResponse
import com.wizeline.heroes.data.remote.characters.HeroesRemoteDataSource
import com.wizeline.heroes.utils.DataStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HeroesRepositoryImp @Inject constructor(
    private val remoteDataSource: HeroesRemoteDataSource
) : HeroesRepository {
    override suspend fun getCharacters(nameStartsWith : String?, offset: Int): Flow<DataStates<DataModel>> = withContext(Dispatchers.IO) {
        remoteDataSource.getCharacters(nameStartsWith, offset)
    }
}