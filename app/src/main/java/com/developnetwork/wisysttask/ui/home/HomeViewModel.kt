package com.developnetwork.wisysttask.ui.home

import androidx.lifecycle.MutableLiveData
import com.developnetwork.wisysttask.data.model.providers.DataItem
import com.developnetwork.wisysttask.data.repositories.ProviderRepo
import com.developnetwork.wisysttask.ui.base.BaseViewModel
import com.developnetwork.wisysttask.ui.base.Status
import com.developnetwork.wisysttask.ui.base.UseCaseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val providerRepo: ProviderRepo): BaseViewModel(){
    val providersLiveData = MutableLiveData<ArrayList<DataItem>>()
    private var mCurrentPage = 1
    private var mTotalPage = 1

    fun getProviders() {
        launch {
            try {
                showLoading.value=true
                val result = withContext(Dispatchers.IO) {
                    providerRepo.getProviders(mCurrentPage)
                }

                result.data?.let { providersLiveData.value?.addAll(it) }
                mCurrentPage = result.currentPage
                mTotalPage = result.totalPages
                showLoading.value=false

            } catch (e: Exception) {
                showLoading.value=false
//                providersLiveData.value =
//                    UseCaseResult.Error(message = errorHandler(e)!!)
            }
        }

    }

    fun loadNextPage() {
        if (mCurrentPage < mTotalPage) {
            mCurrentPage++
            getProviders()
        }
    }
}