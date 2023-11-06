package com.example.myanimelistcleanarchitecture.domain.usecase.staff

import com.example.myanimelistcleanarchitecture.domain.repository.CharactersAndVoiceActorsRepository
import javax.inject.Inject

class UseCaseCharactersVoiceActors @Inject constructor(
    private val charactersAndVoiceActorsRepository: CharactersAndVoiceActorsRepository
) {

    suspend fun getCharactersAndVoiceActors(id: Int) = charactersAndVoiceActorsRepository.charactersAndVoiceActors(id)
}