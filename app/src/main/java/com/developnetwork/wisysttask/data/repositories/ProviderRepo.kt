package com.developnetwork.wisysttask.data.repositories

import com.developnetwork.wisysttask.data.model.providers.Providers
import com.developnetwork.wisysttask.data.network.Service

interface ProviderRepo {
    suspend fun getProviders(page: Int): Providers
    suspend fun filterProviders(
        page: Int,
        key: String?,
        insurance: Int? = 0,
        offers: Int? = 0,
        homeServices: Int? = 0,
        specificationID: Int? = 0
    ): Providers
}

class ProviderRepoImpl(private val service: Service) : ProviderRepo {
    override suspend fun getProviders(page: Int): Providers {
        return service.getProviders(page = page).providers
    }

    override suspend fun filterProviders(
        page: Int,
        key: String?,
        insurance: Int?,
        offers: Int?,
        homeServices: Int?,
        specificationID: Int?
    ): Providers {
        return service.getProviders(
            page = page,
            queryStr = key,
            insurance = insurance,
            offers = offers,
            homeServices = homeServices,
            specificationID = specificationID
        ).providers
    }
}