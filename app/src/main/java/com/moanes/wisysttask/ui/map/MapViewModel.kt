package com.moanes.wisysttask.ui.map

import androidx.lifecycle.MutableLiveData
import com.moanes.wisysttask.data.model.providers.DataItem
import com.moanes.wisysttask.data.repositories.ProviderRepo
import com.moanes.wisysttask.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapViewModel(private val providerRepo: ProviderRepo) : BaseViewModel() {
    val providersLiveData = MutableLiveData<MutableList<DataItem>>()
    var mCurrentPage = 1
    private var mTotalPage = 1

    init {
        providersLiveData.value = ArrayList()
    }

    fun getProviders(currentPage: Int) {
        launch {
            try {
                showLoading.value = true
                val result = withContext(Dispatchers.IO) {
                    providerRepo.getProviders(currentPage)
                }

                result.data?.let { providersLiveData.value?.addAll(it) }
                providersLiveData.value = providersLiveData.value
//                mCurrentPage = result.currentPage
                mTotalPage = result.totalPages

                if (currentPage == 1)
                    getAllProviders()
                showLoading.value = false


            } catch (e: Exception) {
                showLoading.value = false
//                providersLiveData.value =
//                    UseCaseResult.Error(message = errorHandler(e)!!)
            }
        }

    }

    private fun getAllProviders() {
        while (mCurrentPage < mTotalPage) {
            mCurrentPage++
            getProviders(mCurrentPage)
        }
    }
}