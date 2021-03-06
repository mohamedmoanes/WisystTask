package com.moanes.wisysttask.data.model.providers

import com.google.gson.annotations.SerializedName

data class District(@SerializedName("name")
                    val name: String = "",
                    @SerializedName("id")
                    val id: Int = 0)