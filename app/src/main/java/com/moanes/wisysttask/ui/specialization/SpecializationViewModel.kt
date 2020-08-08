package com.moanes.wisysttask.ui.specialization

import androidx.lifecycle.MutableLiveData
import com.androidbuts.multispinnerfilter.KeyPairBoolData
import com.moanes.wisysttask.data.model.specifications.Specification
import com.moanes.wisysttask.data.repositories.SpecificationsRepo
import com.moanes.wisysttask.ui.base.BaseViewModel
import com.moanes.wisysttask.utils.errorHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SpecializationViewModel(    private val specificationsRepo: SpecificationsRepo
):BaseViewModel() {
    val specificationsLiveData = MutableLiveData<List<Specification>>()
    fun getSpecification() {
        launch {
            try {
                showLoading.value = true
                val result = withContext(Dispatchers.IO) {
                    specificationsRepo.getSpecifications()
                }

                if (result.status) {

                    specificationsLiveData.value = result.specifications
                    showLoading.value = false
                } else {
                    showLoading.value = false
                    errorLiveData.postValue(result.msg)
                }

            } catch (e: Exception) {
                showLoading.value = false
                errorLiveData.postValue(errorHandler(e))
            }
        }
    }
}