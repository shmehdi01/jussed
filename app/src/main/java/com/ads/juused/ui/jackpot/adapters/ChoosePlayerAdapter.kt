package com.ads.juused.ui.jackpot.adapters

import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ads.juused.R
import com.ads.juused.databinding.ItemPlayerAddBinding
import com.ads.juused.utility.PPHRankProgress


class ChoosePlayerAdapter() :
    ListAdapter<Any, ChoosePlayerAdapter.ChoosePlayerViewHolder>(diffUtil) {

    inner class ChoosePlayerViewHolder(val binding: ItemPlayerAddBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = true

            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoosePlayerViewHolder =
        ChoosePlayerViewHolder(
            ItemPlayerAddBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ChoosePlayerViewHolder, position: Int) {

        val rainbow = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(
                Color.RED,
                Color.MAGENTA,
                Color.BLUE,
                Color.CYAN,
                Color.GREEN,
                Color.YELLOW,
                Color.RED
            )
        )

        holder.binding.tvPphRankNum.text = "${dummyProgress[position]}"
        holder.binding.tvOppPphRankNum.text = "${dummyProgress2[position]}"

        PPHRankProgress( dummyProgress[position], holder.binding.progressPph)
        PPHRankProgress( dummyProgress2[position], holder.binding.progressOppPph)

    }

    private val dummyProgress = arrayListOf(100.0, 80.0,93.0,100.0,85.0,100.0)
    private val dummyProgress2 = arrayListOf(100.0, 35.0,82.0,82.0,100.0,100.0)

    override fun getItemCount(): Int {
        return 6
    }
}