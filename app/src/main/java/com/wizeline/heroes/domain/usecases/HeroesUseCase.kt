package com.wizeline.heroes.domain.usecases

import com.wizeline.heroes.domain.repository.HeroesRepository
import javax.inject.Inject

class HeroesUseCase @Inject constructor(
    private val repository: HeroesRepository
) {
    suspend operator fun invoke(offset : Int) =
        repository.getCharacters(offset)
}