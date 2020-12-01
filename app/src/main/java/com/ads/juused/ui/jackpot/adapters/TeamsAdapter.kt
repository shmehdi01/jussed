package com.ads.juused.ui.jackpot.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ads.juused.databinding.ItemJackpotBinding
import com.ads.juused.databinding.ItemTeamVsBinding

class TeamsAdapter() :
    ListAdapter<Any, RecyclerView.ViewHolder>(diffUtil) {

    inner class TeamViewHolder(binding: ItemTeamVsBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = true

            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        TeamViewHolder(
            ItemTeamVsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 6
    }
}