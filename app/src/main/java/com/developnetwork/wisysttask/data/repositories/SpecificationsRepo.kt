package com.developnetwork.wisysttask.data.repositories

import com.developnetwork.wisysttask.data.model.specifications.Specification
import com.developnetwork.wisysttask.data.model.specifications.SpecificationsResponse
import com.developnetwork.wisysttask.data.network.Service

interface SpecificationsRepo {
    suspend fun getSpecifications(): List<Specification>
}

class SpecificationsRepoImpl(private val service: Service) : SpecificationsRepo {
    override suspend fun getSpecifications(): List<Specification> {
        return service.getSpecifications().specifications
    }
}