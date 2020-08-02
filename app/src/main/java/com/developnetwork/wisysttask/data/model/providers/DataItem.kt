package com.developnetwork.wisysttask.data.model.providers

import com.google.gson.annotations.SerializedName

data class DataItem(@SerializedName("is_branch")
                    val isBranch: Int = 0,
                    @SerializedName("has_home_services")
                    val hasHomeServices: Int = 0,
                    @SerializedName("has_doctors")
                    val hasDoctors: Int = 0,
                    @SerializedName("distance")
                    val distance: String = "",
                    @SerializedName("city")
                    val city: City,
                    @SerializedName("latitude")
                    val latitude: String = "",
                    @SerializedName("commercial_no")
                    val commercialNo: String = "",
                    @SerializedName("has_clinic_services")
                    val hasClinicServices: Int = 0,
                    @SerializedName("type")
                    val type: Type,
                    @SerializedName("rate")
                    val rate: String = "",
                    @SerializedName("provider")
                    val provider: Provider,
                    @SerializedName("street")
                    val street: String = "",
                    @SerializedName("price")
                    val price: String = "",
                    @SerializedName("has_insurance")
                    val hasInsurance: Int = 0,
                    @SerializedName("logo")
                    val logo: String = "",
                    @SerializedName("id")
                    val id: Int = 0,
                    @SerializedName("email")
                    val email: String = "",
                    @SerializedName("longitude")
                    val longitude: String = "",
                    @SerializedName("has_home_visit")
                    val hasHomeVisit: String = "",
                    @SerializedName("address")
                    val address: String = "",
                    @SerializedName("is_lottery")
                    val isLottery: Int = 0,
                    @SerializedName("type_id")
                    val typeId: Null = null,
                    @SerializedName("mobile")
                    val mobile: String = "",
                    @SerializedName("favourite")
                    val favourite: Int = 0,
                    @SerializedName("parent_type")
                    val parentType: ParentType,
                    @SerializedName("provider_has_bill")
                    val providerHasBill: Int = 0,
                    @SerializedName("hide")
                    val hide: Boolean = false,
                    @SerializedName("branch_no")
                    val branchNo: String = "",
                    @SerializedName("specification_id")
                    val specificationId: String = "",
                    @SerializedName("rate_count")
                    val rateCount: Int = 0,
                    @SerializedName("district")
                    val district: District,
                    @SerializedName("name")
                    val name: String = "",
                    @SerializedName("provider_id")
                    val providerId: String = "",
                    @SerializedName("district_id")
                    val districtId: String = "",
                    @SerializedName("city_id")
                    val cityId: String = "")