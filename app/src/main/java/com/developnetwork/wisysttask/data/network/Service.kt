package com.developnetwork.wisysttask.data.network

import com.developnetwork.wisysttask.data.model.providers.ProvidersResponse
import com.developnetwork.wisysttask.data.model.specifications.SpecificationsResponse
import retrofit2.http.Field
import retrofit2.http.POST

interface Service {
    @POST("specifications")
    suspend fun getSpecifications(): SpecificationsResponse

    @POST("user/search")
    suspend fun getProviders(
        @Field("page") page: Int? = 1,
        @Field("queryStr") queryStr: String? = null,
        @Field("branch_has_insurance") insurance: Int? = 0,
        @Field("branch_has_offers") offers: Int? = 0,
        @Field("branch_has_home_services") homeServices: Int? = 0,
        @Field("rate") rate: Int? = 0,
        @Field("specification_id") specificationID: Int? = null
    ): ProvidersResponse
}