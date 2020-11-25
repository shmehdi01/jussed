package com.ads.juused.ui.splash

import android.os.Bundle
import android.os.PersistableBundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.ads.juused.R
import com.ads.juused.base.BaseActivity


class SplashScreen : BaseActivity() {

    @BindView(R.id.rv_splash)
    lateinit var rvSplash:RecyclerView

    override fun getResourceId(): Int {
        return R.layout.activity_splash
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        ButterKnife.bind(this)

    }

    fun setUpRecyclerView() {
//        val mLinearLayoutManager = LinearLayoutManager(this)
//        mLinearLayoutManager.stackFromEnd = true
//        rvSplash.setLayoutManager(mLinearLayoutManager)
//        rvSplash.scrollToPosition(mMessages.Count - 1)
    }
}