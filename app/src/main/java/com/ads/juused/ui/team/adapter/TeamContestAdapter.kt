package com.ads.juused.ui.team.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ads.juused.databinding.ItemTeamContestBinding
import com.ads.juused.databinding.ItemTeamLeagueBinding

class TeamContestAdapter: ListAdapter<Any, TeamContestAdapter.TeamContestViewHolder>(diffUtil) {

    inner class TeamContestViewHolder(binding: ItemTeamContestBinding): RecyclerView.ViewHolder(binding.root)

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean  = true

            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamContestViewHolder =
        TeamContestViewHolder(ItemTeamContestBinding.inflate(LayoutInflater.from(parent.context), parent,false))

    override fun onBindViewHolder(holder: TeamContestViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 5
    }
}