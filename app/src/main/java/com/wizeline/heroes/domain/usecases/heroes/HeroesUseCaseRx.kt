package com.wizeline.heroes.domain.usecases.heroes

import com.wizeline.heroes.data.models.model.home.DataModel
import com.wizeline.heroes.data.models.model.home.MarvelResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response

interface HeroesUseCaseRx {
    fun invoke(offset : Int) : Single<Response<MarvelResponse<DataModel>>>
}