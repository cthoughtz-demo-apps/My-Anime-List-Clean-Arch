package com.example.myanimelistcleanarchitecture.domain.repository

import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.model.Response.staff.CharactersAndVoiceActorsResponse
import kotlinx.coroutines.flow.Flow

interface CharactersAndVoiceActorsRepository {

    suspend fun charactersAndVoiceActors(id: Int): Flow<NetworkResult<CharactersAndVoiceActorsResponse>>
}