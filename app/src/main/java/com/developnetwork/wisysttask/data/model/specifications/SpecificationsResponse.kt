package com.developnetwork.wisysttask.data.model.specifications

import com.google.gson.annotations.SerializedName

data class SpecificationsResponse(
    @SerializedName("msg")
    val msg: String = "",
    @SerializedName("errNum")
    val errNum: String = "",
    @SerializedName("status")
    val status: Boolean = false,
    @SerializedName("specifications")
    val specifications: List<Specification>
)