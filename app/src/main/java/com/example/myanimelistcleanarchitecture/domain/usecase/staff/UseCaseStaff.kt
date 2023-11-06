package com.example.myanimelistcleanarchitecture.domain.usecase.staff

import com.example.myanimelistcleanarchitecture.domain.repository.StaffRepository
import javax.inject.Inject

class UseCaseStaff @Inject constructor(
    private val staffRepository: StaffRepository
) {
    suspend fun getStaff(id: Int) = staffRepository.getStaff(id)
}