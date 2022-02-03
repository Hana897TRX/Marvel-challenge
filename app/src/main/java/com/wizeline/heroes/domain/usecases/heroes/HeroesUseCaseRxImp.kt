package com.wizeline.heroes.domain.usecases.heroes

import com.wizeline.heroes.data.models.model.home.DataModel
import com.wizeline.heroes.data.models.model.home.MarvelResponse
import com.wizeline.heroes.domain.repository.characters.HeroesRepositoryRx
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class HeroesUseCaseRxImp @Inject constructor(
    private val heroesRepositoryRx: HeroesRepositoryRx
): HeroesUseCaseRx {
    override fun invoke(offset: Int): Single<Response<MarvelResponse<DataModel>>> =
        heroesRepositoryRx.getCharacters(null, offset)
}