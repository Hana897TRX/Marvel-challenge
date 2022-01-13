package com.wizeline.heroes.domain.repository

import com.wizeline.heroes.data.models.Characters
import com.wizeline.heroes.data.remote.HeroesRemoteDataSource
import com.wizeline.heroes.utils.DataStates
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HeroesRepositoryImp @Inject constructor(
    private val remoteDataSource: HeroesRemoteDataSource
) : HeroesRepository {
    override suspend fun getCharacters(): Flow<DataStates<Characters>> =
        remoteDataSource.getCharacters()
}