package com.ads.juused.ui.team.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ads.juused.databinding.ItemTeamLeagueBinding

class TeamLeagueAdapter(private val itemClick: () -> Unit): ListAdapter<Any, TeamLeagueAdapter.TeamLeagueViewHolder>(diffUtil) {

    inner class TeamLeagueViewHolder(val binding: ItemTeamLeagueBinding): RecyclerView.ViewHolder(binding.root)

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean  = true

            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamLeagueViewHolder =
        TeamLeagueViewHolder(ItemTeamLeagueBinding.inflate(LayoutInflater.from(parent.context), parent,false))

    override fun onBindViewHolder(holder: TeamLeagueViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick()
        }
    }

    override fun getItemCount(): Int {
        return 5
    }
}