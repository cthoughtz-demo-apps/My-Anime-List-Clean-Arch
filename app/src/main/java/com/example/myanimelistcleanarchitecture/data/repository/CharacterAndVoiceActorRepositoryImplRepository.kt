package com.example.myanimelistcleanarchitecture.data.repository

import com.example.myanimelistcleanarchitecture.data.ApiService
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.repository.CharactersAndVoiceActorsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterAndVoiceActorRepositoryImplRepository @Inject constructor(
    private val apiService: ApiService
): CharactersAndVoiceActorsRepository {
    override suspend fun charactersAndVoiceActors(id: Int)= flow {

        emit(NetworkResult.Loading(true))
        val response = apiService.getCharactersAndVoiceActors(id)
        emit(NetworkResult.Success(response))
    }. catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

}