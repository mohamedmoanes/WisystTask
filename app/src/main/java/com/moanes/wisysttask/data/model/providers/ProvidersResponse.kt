package com.moanes.wisysttask.data.model.providers

import com.google.gson.annotations.SerializedName

data class ProvidersResponse(@SerializedName("msg")
                             val msg: String = "",
                             @SerializedName("errNum")
                             val errNum: String = "",
                             @SerializedName("providers")
                             val providers: Providers,
                             @SerializedName("status")
                             val status: Boolean = false)