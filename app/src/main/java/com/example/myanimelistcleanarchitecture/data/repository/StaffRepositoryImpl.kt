package com.example.myanimelistcleanarchitecture.data.repository

import com.example.myanimelistcleanarchitecture.data.ApiService
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.repository.StaffRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StaffRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : StaffRepository{
    override suspend fun getStaff(id: Int) = flow {

        emit(NetworkResult.Loading(true))
        val response = apiService.getStaff(id)
        emit(NetworkResult.Success(response))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }
}