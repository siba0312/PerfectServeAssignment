package com.example.perfectserve.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bolt.interview.repository.ConnectivityManager
import com.bolt.interview.repository.NetworkUrl
import com.bolt.interview.repository.ResultCode
import com.example.perfectserve.model.BusinessDetail
import java.util.ArrayList

class LandingScreenViewModel : ViewModel(), ConnectivityManager.FetchNetworkResponse {

    var businessDetailMutableLiveData: MutableLiveData<BusinessDetail>? = MutableLiveData<BusinessDetail>()
    var errorMessageCode = MutableLiveData<String>()

    //Call network and get business items
    fun getBusinessListFromNetwork(queryParam: String, context: Context) {
        val connectivityManager = ConnectivityManager(this, context)
        if (connectivityManager.isInternetAvailable(context)) {
            connectivityManager.fetchFeedFromNetwork(
                NetworkUrl.getBusinessSearchUrl(queryParam),
                BusinessDetail::class.java
            )
        } else {
            errorMessageCode.postValue(ResultCode.NETWORK_NOT_AVAILABLE.msg)
        }
    }

    override fun <T> onSuccess(modelList: List<T>?) {
        val businessDetailArrayList: ArrayList<BusinessDetail> = modelList as ArrayList<BusinessDetail>
        businessDetailMutableLiveData?.postValue(businessDetailArrayList[0])
    }

    override fun onFailure(msg: String) {
        errorMessageCode.postValue(msg)
    }
}