package com.ads.juused.base

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ads.juused.model.FailureResponse

abstract class RichMediatorLiveData<T> : MediatorLiveData<T>() {

    private lateinit var errorLiveData: MutableLiveData<Throwable>
    private lateinit var failureResponseLiveData: MutableLiveData<FailureResponse>

    private fun initLiveData() {
        errorLiveData = MutableLiveData()
        failureResponseLiveData = MutableLiveData()
    }

    protected abstract fun getFailureObserver(): Observer<FailureResponse>

    protected abstract fun getErrorObserver(): Observer<Throwable>

    override fun onInactive() {
        super.onInactive()
        removeSource(failureResponseLiveData)
        removeSource(errorLiveData)
    }

    override fun onActive() {
        super.onActive()
        initLiveData()
        addSource(failureResponseLiveData, getFailureObserver())
        addSource(errorLiveData, getErrorObserver())
    }

    open fun setFailure(failureResponse: FailureResponse?) {
        try {
            failureResponseLiveData.setValue(failureResponse)
        } catch (ignore: NullPointerException) {
            ignore.printStackTrace()
        }
    }

    open fun setError(t: Throwable?) {
        try {
            errorLiveData.setValue(t)
        } catch (ignore: NullPointerException) {
            ignore.printStackTrace()
        }
    }

}