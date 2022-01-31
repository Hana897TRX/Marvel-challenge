package com.wizeline.heroes.domain.usecases.search

import com.wizeline.heroes.data.models.model.home.DataModel
import com.wizeline.heroes.domain.repository.characters.HeroesRepository
import com.wizeline.heroes.utils.DataStates
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCaseImp @Inject constructor(
    private val repository: HeroesRepository
) : SearchUseCase {
    override suspend fun searchCharacters(
        nameStartsWith: String?,
        offset: Int
    ) = repository.getCharacters(nameStartsWith, offset)
}