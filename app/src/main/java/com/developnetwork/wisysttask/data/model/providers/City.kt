package com.developnetwork.wisysttask.data.model.providers

import com.google.gson.annotations.SerializedName

data class City(@SerializedName("name")
                val name: String = "",
                @SerializedName("id")
                val id: Int = 0)