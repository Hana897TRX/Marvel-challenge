package com.wizeline.heroes.domain.repository.characters

import com.wizeline.heroes.data.models.model.home.DataModel
import com.wizeline.heroes.data.models.model.home.MarvelResponse
import com.wizeline.heroes.utils.DataStates
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface HeroesRepositoryRx {
    fun getCharacters(nameStartsWith : String?, offset: Int) : Single<Response<MarvelResponse<DataModel>>>
}