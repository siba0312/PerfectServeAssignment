package com.bolt.interview.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.perfectserve.R
import com.example.perfectserve.application.AppController
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.util.*
import kotlin.collections.HashMap

class ConnectivityManager() {

    private val API_HEADER_AUTHORIZATION_KEY= "Authorization"
    private val API_HEADER_AUTHORIZATION_VALUE = "Bearer O5H9A0D9qSnN2Q-MQerhCuUljXnNlRaYGZdxv0HM5SLfznvtTDj_lwhMg-_RF7Tq-pwB7-KeNvoFRDoEL0Or7xndhButRGOohZn2l8nanLQAIIe2MISSmvw525SmYXYx "

    constructor(dataResponse: FetchNetworkResponse?, context: Context?) : this() {
        this.dataResponse = dataResponse
        mContext = context
    }

    /**
     * Interface for callback
     */
    interface FetchNetworkResponse {
        fun <T> onSuccess(modelList: List<T>?)
        fun onFailure(msg: String)
    }

    /**
     * TAG for logs
     */
    private val TAG = javaClass.simpleName

    private var gson: Gson? = null

    /**
     * [FetchNetworkResponse] callback listener object
     */
    private var dataResponse: FetchNetworkResponse? = null

    /**
     * Context
     */
    private var mContext: Context? = null


    /**
     * Method to fetch feed from the network
     */
    fun <T> fetchFeedFromNetwork(url: String, modelClass: Class<T>) {
        Log.d(TAG, "fetchFeedFromNetwork")
        Log.d(TAG, "url = $url")
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(mContext)
        // Request a string response from the provided URL.
        val stringRequest: StringRequest = object : StringRequest(
            Method.GET, url,
            Response.Listener { response ->
                Log.d(TAG, "on Success")
                handleResponse(response, modelClass)
            },
            Response.ErrorListener {
                dataResponse?.onFailure(
                    mContext?.getString(
                        R.string.something_went_wrong
                    ) ?: ResultCode.DEFAULT_ERROR.msg
                )
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headerHashMap = HashMap<String, String>()
                headerHashMap[API_HEADER_AUTHORIZATION_KEY] = API_HEADER_AUTHORIZATION_VALUE
                return headerHashMap
            }

            override fun getParams(): Map<String, String>? {
                return HashMap()
            }
        }

        // Add the request to the RequestQueue.
        Log.d("StringRequest", stringRequest.url)
        AppController.getInstance()?.addToRequestQueue(stringRequest)
    }

    /**
     * Method to handle the response data either fetched from network or cache
     *
     * @param response String
     */
    private fun <T> handleResponse(response: String, modelClass: Class<T>) {
        Log.d(TAG, "handle response")
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setDateFormat("M/d/yy hh:mm a")
        gson = gsonBuilder.create()
        val modelClass = gson?.fromJson(response, modelClass)
        val modelList: List<T?> = ArrayList(listOf(modelClass))
        dataResponse?.onSuccess(modelList)
    }

    /**
     * Method to check the availability of internet
     *
     * @param context [Context]
     * @return boolean
     */
    fun isInternetAvailable(context: Context?): Boolean {
        val cm = (context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

}
