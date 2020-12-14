package com.ads.juused.utility

import android.content.res.ColorStateList
import android.graphics.Color
import com.ads.juused.databinding.MutlitColorProgressBinding
import kotlin.math.roundToInt

class PPHRankProgress(progress: Double, binding: MutlitColorProgressBinding) {

    private val colors = intArrayOf(
        Color.parseColor("#FF3030"),
        Color.parseColor("#FF3030"),
        Color.parseColor("#FF3E30"),
        Color.parseColor("#FF6F30"),
        Color.parseColor("#FF9730"),
        Color.parseColor("#FFC130"),
        Color.parseColor("#ADFF30"),
        Color.parseColor("#37FF30"),
        Color.parseColor("#30FF97"),
        Color.parseColor("#30B8FF"),
        Color.parseColor("#30F1FF")
    )

    val disabledColor = Color.parseColor("#2D2D2D")

    init {
        val views = arrayListOf(
            binding.v1,
            binding.v2,
            binding.v3,
            binding.v4,
            binding.v5,
            binding.v6,
            binding.v7,
            binding.v8,
            binding.v9,
            binding.v10,
            binding.v11
        )

        var fillingSection = ((11 / 100.0) * progress).roundToInt()

        if(fillingSection > 11) {
           fillingSection = 11
        }

        for(i in 0 until fillingSection) {
            views[i].backgroundTintList = ColorStateList.valueOf(colors[i])
        }
    }
}