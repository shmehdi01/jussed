package com.ads.juused.ui.player.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.ads.juused.base.BaseRecyclerAdapter
import com.ads.juused.base.BaseViewHolder
import com.ads.juused.databinding.ItemLeagueOptionBinding
import com.ads.juused.databinding.ItemMatchesBinding

class PlayerMatchesAdapter : BaseRecyclerAdapter<Any, PlayerMatchesAdapter.PlayerMatchesViewHolder>(diffUtil) {

    inner class PlayerMatchesViewHolder(binding: ItemMatchesBinding) :
        BaseViewHolder<Any>(binding) {

        override fun bindTo(data: Any) {
        }
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): PlayerMatchesViewHolder =
        PlayerMatchesViewHolder(ItemMatchesBinding.inflate(inflater, parent, false))

    override fun onBindViewHolder(holder: PlayerMatchesViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onClick?.invoke(null)
        }
    }

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = true

            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = true
        }
    }

}