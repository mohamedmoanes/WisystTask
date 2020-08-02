package com.developnetwork.wisysttask.data.model.specifications

import com.google.gson.annotations.SerializedName

data class Specification(@SerializedName("name")
                         val name: String = "",
                         @SerializedName("id")
                         val id: Int = 0)