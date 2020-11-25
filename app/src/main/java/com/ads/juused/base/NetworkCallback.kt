package com.ads.juused.base

import android.util.Log
import com.ads.juused.model.FailureResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class NetworkCallback<T> : Callback<T> {

    abstract fun onSuccess(t: T)

    abstract fun onFailure(failureResponse: FailureResponse)

    abstract fun onError(t: Throwable)

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            Log.e("network", "is succesfull" + response.body())
            onSuccess(response.body()!!)
        }
    }

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        if (t is SocketTimeoutException || t is UnknownHostException) {
            val failureResponseForNoNetwork = getFailureResponseForNoNetwork()
            onFailure(failureResponseForNoNetwork)
        } else {
            onError(t!!)
        }
    }

    private fun getFailureResponseForNoNetwork(): FailureResponse {
        val failureResponse = FailureResponse()
        failureResponse.setErrorMessage("No Network")
        failureResponse.setErrorCode(9)
        return failureResponse
    }

}

