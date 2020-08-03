package com.moanes.wisysttask.data.repositories

import com.moanes.wisysttask.data.model.specifications.SpecificationsResponse
import com.moanes.wisysttask.data.network.Service

interface SpecificationsRepo {
    suspend fun getSpecifications(): SpecificationsResponse
}

class SpecificationsRepoImpl(private val service: Service) : SpecificationsRepo {
    override suspend fun getSpecifications(): SpecificationsResponse {
        return service.getSpecifications()
    }
}