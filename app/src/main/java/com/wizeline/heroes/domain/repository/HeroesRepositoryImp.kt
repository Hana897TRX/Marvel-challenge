package com.wizeline.heroes.domain.repository

import com.wizeline.heroes.data.models.CharacterModel
import com.wizeline.heroes.data.remote.HeroesRemoteDataSource
import com.wizeline.heroes.utils.DataStates
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HeroesRepositoryImp @Inject constructor(
    private val remoteDataSource: HeroesRemoteDataSource
) : HeroesRepository {
    override suspend fun getCharacters(offset : Int): Flow<DataStates<CharacterModel>> =
        remoteDataSource.getCharacters(offset)
}