package com.moanes.wisysttask.data.repositories

import com.moanes.wisysttask.data.model.specifications.Specification
import com.moanes.wisysttask.data.network.Service

interface SpecificationsRepo {
    suspend fun getSpecifications(): List<Specification>
}

class SpecificationsRepoImpl(private val service: Service) : SpecificationsRepo {
    override suspend fun getSpecifications(): List<Specification> {
        return service.getSpecifications().specifications
    }
}