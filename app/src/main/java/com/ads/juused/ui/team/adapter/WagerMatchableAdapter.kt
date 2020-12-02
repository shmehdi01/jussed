package com.ads.juused.ui.team.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ads.juused.databinding.ItemWagerMatchableBinding
import com.ads.juused.databinding.ItemWagerNotMatchableBinding

class WagerMatchableAdapter(private val viewType: Int) :
    ListAdapter<Any, RecyclerView.ViewHolder>(diffUtil) {

    inner class WagerMatchableViewHolder(binding: ItemWagerMatchableBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class WagerNotMatchableViewHolder(binding: ItemWagerNotMatchableBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {

        const val MATCHABLE = 0
        const val NOT_MATCHABLE = 1

        val diffUtil = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = true

            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (this.viewType == MATCHABLE) WagerMatchableViewHolder(
            ItemWagerMatchableBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        else WagerNotMatchableViewHolder(
            ItemWagerNotMatchableBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 4
    }
}