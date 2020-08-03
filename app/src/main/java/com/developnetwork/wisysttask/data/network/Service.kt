package com.developnetwork.wisysttask.data.network

import com.developnetwork.wisysttask.data.model.providers.ProvidersResponse
import com.developnetwork.wisysttask.data.model.specifications.SpecificationsResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Service {
    @POST("specifications")
    @FormUrlEncoded
    suspend fun getSpecifications(
        @Field("api_email") email: String = "api.auth@hs.info",
        @Field("api_password") password: String = "Ka@r%*MoAJ!rtPXz",
        @Field("lang") lang: String = "en"
    ): SpecificationsResponse

    @POST("user/search")
    @FormUrlEncoded
    suspend fun getProviders(
        @Field("api_email") email: String = "api.auth@hs.info",
        @Field("api_password") password: String = "Ka@r%*MoAJ!rtPXz",
        @Field("lang") lang: String = "en",
        @Field("page") page: Int? = 1,
        @Field("queryStr") queryStr: String? = null,
        @Field("branch_has_insurance") insurance: Int? = 0,
        @Field("branch_has_offers") offers: Int? = 0,
        @Field("branch_has_home_services") homeServices: Int? = 0,
        @Field("specification_id") specificationID: Int? = null
    ): ProvidersResponse
}