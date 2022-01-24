package com.wizeline.heroes.domain.repository

import com.wizeline.heroes.data.models.CharacterModel
import com.wizeline.heroes.data.remote.HeroesRemoteDataSource
import com.wizeline.heroes.utils.DataStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HeroesRepositoryImp @Inject constructor(
    private val remoteDataSource: HeroesRemoteDataSource
) : HeroesRepository {
    override suspend fun getCharacters(offset : Int): Flow<DataStates<CharacterModel>> = withContext(Dispatchers.IO) {
        remoteDataSource.getCharacters(offset)
    }
}