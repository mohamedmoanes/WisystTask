package com.moanes.wisysttask.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.androidbuts.multispinnerfilter.KeyPairBoolData
import com.moanes.wisysttask.data.model.providers.DataItem
import com.moanes.wisysttask.data.model.specifications.Specification
import com.moanes.wisysttask.data.repositories.ProviderRepo
import com.moanes.wisysttask.data.repositories.SpecificationsRepo
import com.moanes.wisysttask.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val providerRepo: ProviderRepo,private val specificationsRepo: SpecificationsRepo) : BaseViewModel() {
    val providersLiveData = MutableLiveData<MutableList<DataItem>>()
    private var mCurrentPage = 1
    private var mTotalPage = 1
    var key: String? = null
    var insurance: Int? = 0
    var offers: Int? = 0
    var homeServices: Int? = 0
    var specificationID: Int? = 0

    init {
        providersLiveData.value = ArrayList()
    }

    fun getProviders() {
        launch {
            try {
                showLoading.value = true
                val result = withContext(Dispatchers.IO) {
                    providerRepo.getProviders(mCurrentPage)
                }

                result.data?.let { providersLiveData.value?.addAll(it) }
                providersLiveData.value = providersLiveData.value
                mCurrentPage = result.currentPage
                mTotalPage = result.totalPages
                showLoading.value = false

            } catch (e: Exception) {
                showLoading.value = false
//                providersLiveData.value =
//                    UseCaseResult.Error(message = errorHandler(e)!!)
            }
        }

    }

    fun loadNextPage() {
        if (mCurrentPage < mTotalPage) {
            mCurrentPage++
            if (key != null || insurance != 0 || offers != 0 || homeServices != 0 || specificationID != 0)
                filter()
            else
                getProviders()
        }
    }

    fun filter(reset: Boolean = false) {
        if (reset) {
            mCurrentPage = 1
        reset()
        }
        launch {
            try {
                showLoading.value = true
                val result = withContext(Dispatchers.IO) {
                    providerRepo.filterProviders(
                        mCurrentPage,
                        key,
                        insurance,
                        offers,
                        homeServices,
                        specificationID
                    )
                }

                result.data?.let { providersLiveData.value?.addAll(it) }
                providersLiveData.value = providersLiveData.value
                mCurrentPage = result.currentPage
                mTotalPage = result.totalPages
                showLoading.value = false

            } catch (e: Exception) {
                showLoading.value = false
//                providersLiveData.value =
//                    UseCaseResult.Error(message = errorHandler(e)!!)
            }
        }
    }

    fun getSpecification()= liveData(Dispatchers.IO) {
        try {
            showLoading.postValue(true)

            val list =ArrayList<KeyPairBoolData>()
            for(item :Specification in specificationsRepo.getSpecifications()){
                val tmp =KeyPairBoolData()
                tmp.id=item.id.toLong()
                tmp.name=item.name
                list.add(tmp)
            }

            emit(list)
            showLoading.postValue(false)
        } catch (exception: Exception) {
            showLoading.postValue(false)
        }
    }

    private fun reset(){
        providersLiveData.value?.clear()
    }
}