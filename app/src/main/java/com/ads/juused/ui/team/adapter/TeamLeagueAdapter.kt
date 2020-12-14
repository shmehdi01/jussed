package com.ads.juused.ui.team.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ads.juused.databinding.ItemTeamLeagueBinding
import com.ads.juused.utility.show

class TeamLeagueAdapter(private val itemClick: () -> Unit): ListAdapter<Any, TeamLeagueAdapter.TeamLeagueViewHolder>(diffUtil) {

    private var hideDateTime: Boolean = false

    inner class TeamLeagueViewHolder(val binding: ItemTeamLeagueBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.tvDateTime.show(!hideDateTime)
        }
    }

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

    fun hideDateAndTime(hide: Boolean) {
        this.hideDateTime = hide
    }

    override fun getItemCount(): Int {
        return 5
    }
}