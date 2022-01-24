package com.wizeline.heroes.data.remote

import com.wizeline.heroes.data.models.model.home.CharacterModel
import com.wizeline.heroes.data.network.HeroesService
import com.wizeline.heroes.utils.DataStates
import com.wizeline.heroes.utils.DataUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.Exception

class HeroesRemoteDataSourceImp @Inject constructor(
    private val service: HeroesService
) : HeroesRemoteDataSource {
    override suspend fun getCharacters(offset: Int): Flow<DataStates<CharacterModel>> = flow {
        try {
            val response =
                service.characters(
                    offset,
                    DataUtils.TIME_STAMP,
                    DataUtils.API_KEY,
                    DataUtils.getHash()
                )
            print(response)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(DataStates.Success(it))
                } ?: emit(DataStates.Error(response.code(), response.message()))
            } else {
                emit(DataStates.Error(response.code(), response.message()))
            }
        } catch (e: Exception) {
            emit(DataStates.Error(0, e.message ?: ""))
        }
    }
}