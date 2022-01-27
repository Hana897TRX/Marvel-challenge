package com.wizeline.heroes.data.remote.comics

import com.wizeline.heroes.data.models.model.series.SeriesModel
import com.wizeline.heroes.data.network.HeroesService
import com.wizeline.heroes.utils.ConstVals.EMPTY_VALUE
import com.wizeline.heroes.utils.DataStates
import com.wizeline.heroes.utils.DataUtils
import com.wizeline.heroes.utils.SerieMapper.toMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ComicsRemoteDataSourceImp @Inject constructor(
    private val service : HeroesService
): ComicsRemoteDataSource {
    override suspend fun getComics(characterId: String): Flow<DataStates<SeriesModel>> = flow {
        try {
            val response =
                service.getComics(
                    characterId,
                    DataUtils.TIME_STAMP,
                    DataUtils.API_KEY,
                    DataUtils.getHash()
                )
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(DataStates.Success(it.data.toMap()))
                } ?: emit(DataStates.Error(response.code(), response.message()))
            } else {
                emit(DataStates.Error(response.code(), response.message()))
            }
        } catch (e: Exception) {
            emit(DataStates.Error(0, e.message ?: EMPTY_VALUE))
        }
    }
}