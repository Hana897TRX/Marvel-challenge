package com.wizeline.heroes.data.remote.characters

import com.wizeline.heroes.data.models.model.home.DataModel
import com.wizeline.heroes.data.models.model.home.MarvelResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response

interface HeroesRemoteDataSourceRx {
    fun getCharacters(nameStartsWith : String?, offset: Int) : Single<Response<MarvelResponse<DataModel>>>
}