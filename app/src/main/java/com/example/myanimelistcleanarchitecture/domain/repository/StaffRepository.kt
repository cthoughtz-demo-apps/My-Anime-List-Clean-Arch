package com.example.myanimelistcleanarchitecture.domain.repository

import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.domain.model.Response.staff.AnimeStaffResponse
import kotlinx.coroutines.flow.Flow

interface StaffRepository {

    suspend fun getStaff(id: Int): Flow<NetworkResult<AnimeStaffResponse>>
}