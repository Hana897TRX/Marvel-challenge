package com.wizeline.heroes.domain.repository.characters

import com.wizeline.heroes.data.models.model.home.DataModel
import com.wizeline.heroes.data.models.model.home.MarvelResponse
import com.wizeline.heroes.data.remote.characters.HeroesRemoteDataSourceRx
import com.wizeline.heroes.utils.DataStates
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class HeroesRepositoryRxImp @Inject constructor(
    private val dataSourceRx: HeroesRemoteDataSourceRx
): HeroesRepositoryRx {
    override fun getCharacters(
        nameStartsWith: String?,
        offset: Int
    ): Single<Response<MarvelResponse<DataModel>>> =
        dataSourceRx.getCharacters(nameStartsWith, offset)
}