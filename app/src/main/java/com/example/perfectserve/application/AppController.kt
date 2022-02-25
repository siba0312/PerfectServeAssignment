package com.example.perfectserve.application

import android.app.Application
import android.text.TextUtils
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class AppController : Application() {

    val TAG = AppController::class.java.simpleName
    var networkRequestQueue: RequestQueue? = null

    companion object {
        var appControlerInstance: AppController? = null
        fun getInstance(): AppController? {
            return appControlerInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
        appControlerInstance = this
    }

    fun getRequestQueue(): RequestQueue? {
        Log.d(TAG, "getRequestQueue")
        if (networkRequestQueue == null) {
            networkRequestQueue = Volley.newRequestQueue(applicationContext)
        }
        return networkRequestQueue
    }

    fun <T> addToRequestQueue(req: Request<T>, tag: String?) {
        Log.d(TAG, "addToRequestQueue")
        req.tag = if (TextUtils.isEmpty(tag)) TAG else tag
        getRequestQueue()?.add(req)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        Log.d(TAG, "addToRequestQueue")
        req.tag = TAG
        getRequestQueue()?.add(req)
    }

    fun cancelPendingRequests(tag: Any?) {
        Log.d(TAG, "cancelPendingRequests")
        if (networkRequestQueue != null) {
            networkRequestQueue?.cancelAll(tag)
        }
    }
}
