package com.developnetwork.wisysttask.data.model.providers

import com.developnetwork.wisysttask.data.model.providers.ParentType
import com.google.gson.annotations.SerializedName

data class Provider(@SerializedName("is_branch")
                    val isBranch: Int = 0,
                    @SerializedName("hide")
                    val hide: Boolean = false,
                    @SerializedName("rate_count")
                    val rateCount: Int = 0,
                    @SerializedName("is_lottery")
                    val isLottery: Int = 0,
                    @SerializedName("has_insurance")
                    val hasInsurance: Int = 0,
                    @SerializedName("name")
                    val name: String = "",
                    @SerializedName("logo")
                    val logo: String = "",
                    @SerializedName("id")
                    val id: Int = 0,
                    @SerializedName("parent_type")
                    val parentType: ParentType,
                    @SerializedName("provider_has_bill")
                    val providerHasBill: Int = 0)