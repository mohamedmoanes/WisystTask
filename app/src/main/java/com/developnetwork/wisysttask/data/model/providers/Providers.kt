package com.developnetwork.wisysttask.data.model.providers

import com.developnetwork.wisysttask.data.model.providers.DataItem
import com.google.gson.annotations.SerializedName

data class Providers(@SerializedName("data")
                     val data: List<DataItem>?,
                     @SerializedName("total_count")
                     val totalCount: Int = 0,
                     @SerializedName("total_pages")
                     val totalPages: Int = 0,
                     @SerializedName("current_page")
                     val currentPage: Int = 0)