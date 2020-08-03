package com.moanes.wisysttask.ui.map

import androidx.lifecycle.MutableLiveData
import com.moanes.wisysttask.data.model.providers.DataItem
import com.moanes.wisysttask.data.repositories.ProviderRepo
import com.moanes.wisysttask.ui.base.BaseViewModel
import com.moanes.wisysttask.utils.errorHandler
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
                if (result.status) {
                    result.providers.data?.let { providersLiveData.value?.addAll(it) }
                    providersLiveData.value = providersLiveData.value
//                mCurrentPage = result.currentPage
                    mTotalPage = result.providers.totalPages

                    if (currentPage == 1)
                        getAllProviders()
                    showLoading.value = false
                } else {
                    showLoading.value = false
                    errorLiveData.postValue(result.msg)
                }



            } catch (e: Exception) {
                showLoading.value = false
                errorLiveData.postValue(errorHandler(e))
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