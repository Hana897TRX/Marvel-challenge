package com.wizeline.heroes.data.remote.characters

import com.wizeline.heroes.data.models.model.home.DataModel
import com.wizeline.heroes.data.models.model.home.MarvelResponse
import com.wizeline.heroes.data.network.HeroesService
import com.wizeline.heroes.utils.ConstVals.EMPTY_VALUE
import com.wizeline.heroes.utils.DataStates
import com.wizeline.heroes.utils.DataUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.Exception

class HeroesRemoteDataSourceImp @Inject constructor(
    private val service: HeroesService
) : HeroesRemoteDataSource {
    override suspend fun getCharacters(nameStartsWith : String?, offset: Int): Flow<DataStates<DataModel>> = flow {
        try {
            val response =
                service.characters(
                    nameStartsWith = if(nameStartsWith == EMPTY_VALUE) null else nameStartsWith,
                    offset = offset,
                    ts = DataUtils.TIME_STAMP,
                    apikey = DataUtils.API_KEY,
                    hash = DataUtils.getHash()
                )
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(DataStates.Success(it.data))
                } ?: emit(DataStates.Error(response.code(), response.message()))
            } else {
                emit(DataStates.Error(response.code(), response.message()))
            }
        } catch (e: Exception) {
            emit(DataStates.Error(0, e.message ?: EMPTY_VALUE))
        }
    }
}