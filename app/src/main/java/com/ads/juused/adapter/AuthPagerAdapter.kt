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
        "Pick Up",
        "Draft Position Groups"
    )

    private val header2 = arrayListOf(
        "",
        "Game",
        "not Players"
    )

    val descriptions = arrayListOf(
        "",
        "H2H matchups made better. Let the draw of the players determine your fate",
        "A modernized approach to Fantasy. Don't worry about players just teams")


    override fun getItem(position: Int): Fragment = BannerFragment.newInstance(images[position], header1[position], header2[position])

    override fun getCount(): Int = images.size

}