package solo.android.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ads.juused.network.resource.Loading
import com.ads.juused.network.resource.Resource

abstract class BaseViewModel: ViewModel() {

    protected val _loading = MutableLiveData<Loading>()
    protected val _error = MutableLiveData<Resource.Error>()

    val loading : LiveData<Loading>
        get() = _loading

    val error : LiveData<Resource.Error>
        get() = _error
}