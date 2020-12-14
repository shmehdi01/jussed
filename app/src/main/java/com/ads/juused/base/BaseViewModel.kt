package com.ads.juused.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ads.juused.network.resource.Loading
import com.ads.juused.network.resource.Resource

abstract class BaseViewModel : ViewModel() {

    private val _loading = MutableLiveData<Loading>()
    private val _error = MutableLiveData<Resource.Error>()

    val loader: LiveData<Loading>
        get() = _loading

    val error: LiveData<Resource.Error>
        get() = _error

    var Resource.Error.notifyError
        get() = this
        set(_) {
            _error.value = this
        }

    var Loading.notifyLoading
        get() = _loading.value!!
        set(value) {
            _loading.value = value
        }
}