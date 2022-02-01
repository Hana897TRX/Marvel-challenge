package com.wizeline.heroes.domain.usecases.heroes

import com.wizeline.heroes.domain.repository.characters.HeroesRepository
import javax.inject.Inject

class HeroesUseCaseImp @Inject constructor(
    private val repository: HeroesRepository
) : HeroesUseCase {
    override suspend operator fun invoke(offset : Int) =
        repository.getCharacters(null, offset)
}