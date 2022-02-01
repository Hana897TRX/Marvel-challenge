package com.wizeline.heroes.data.remote.characters

import com.wizeline.heroes.data.models.model.home.DataModel
import com.wizeline.heroes.data.models.model.home.MarvelResponse
import com.wizeline.heroes.data.network.HeroesService
import com.wizeline.heroes.utils.DataUtils
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class HeroesRemoteDataSourceRxImp @Inject constructor(
    private val service: HeroesService
) : HeroesRemoteDataSourceRx {
    override fun getCharacters(
        nameStartsWith: String?,
        offset: Int
    ): Single<Response<MarvelResponse<DataModel>>> {
        return service.charactersRx(
            offset = offset,
            nameStartsWith = nameStartsWith,
            ts = DataUtils.TIME_STAMP,
            apikey = DataUtils.API_KEY,
            hash = DataUtils.getHash()
        )
    }
}