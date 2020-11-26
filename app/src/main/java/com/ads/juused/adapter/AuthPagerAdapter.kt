package com.ads.juused.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ads.juused.R
import com.ads.juused.ui.auth.fragments.BannerFragment

class AuthSliderAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val images = arrayListOf(
        -1,
        R.drawable.car,
        R.drawable.car
    )

    private val header1 = arrayListOf(
        "",
        context.getString(R.string.pick_up),
        context.getString(R.string.draft_position_groups)
    )

    private val header2 = arrayListOf(
        "",
        context.getString(R.string.game),
        context.getString(R.string.lazy_player)
    )

    val descriptions = arrayListOf(
        "",
        context.getString(R.string.auth_slider_desc_1),
        context.getString(R.string.auth_slider_desc_2))


    override fun getItem(position: Int): Fragment = BannerFragment.newInstance(images[position], header1[position], header2[position])

    override fun getCount(): Int = images.size

}