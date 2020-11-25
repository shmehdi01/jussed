package com.ads.juused.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import com.ads.juused.R


abstract class BaseActivity : AppCompatActivity() {

    lateinit var baseContainer: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.base_activity)
        baseContainer = findViewById(R.id.rl_base_container)
        setLayout()
        ButterKnife.bind(this)
    }

    abstract fun getResourceId(): Int
    open fun setLayout() {
        if (getResourceId() != -1) {
            removeLayout()
            val layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            )
            val layoutInflater =
                getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            if (layoutInflater != null) {
                val view = layoutInflater.inflate(getResourceId(), null)
                baseContainer.addView(view, layoutParams)
            }
        }
    }

    open fun removeLayout() {
        if (baseContainer.childCount >= 1) baseContainer.removeAllViews()
    }
}