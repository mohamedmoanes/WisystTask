package com.moanes.wisysttask.data.repositories

import com.moanes.wisysttask.data.model.providers.ProvidersResponse
import com.moanes.wisysttask.data.network.Service

interface ProviderRepo {
    suspend fun getProviders(page: Int): ProvidersResponse
    suspend fun filterProviders(
        page: Int,
        key: String?,
        insurance: Int? = 0,
        offers: Int? = 0,
        homeServices: Int? = 0,
        specificationID: Int? = 0
    ): ProvidersResponse
}

class ProviderRepoImpl(private val service: Service) : ProviderRepo {
    override suspend fun getProviders(page: Int): ProvidersResponse {
        return service.getProviders(page = page)
    }

    override suspend fun filterProviders(
        page: Int,
        key: String?,
        insurance: Int?,
        offers: Int?,
        homeServices: Int?,
        specificationID: Int?
    ): ProvidersResponse {
        return service.getProviders(
            page = page,
            queryStr = key,
            insurance = insurance,
            offers = offers,
            homeServices = homeServices,
            specificationID = specificationID
        )
    }
}