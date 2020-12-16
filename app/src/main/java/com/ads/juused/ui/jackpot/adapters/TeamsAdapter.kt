package com.ads.juused.ui.jackpot.adapters

import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.util.containsValue
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ads.juused.R
import com.ads.juused.databinding.ItemTeamVsBinding

class TeamsAdapter(private val onSelection: ((Boolean) -> Unit)? = null) :
    ListAdapter<Any, TeamsAdapter.TeamViewHolder>(diffUtil) {

    inner class TeamViewHolder(val binding: ItemTeamVsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var context: Context = binding.root.context
    }

    private val sparseBooleanArray = SparseBooleanArray()

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = true

            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder =
        TeamViewHolder(
            ItemTeamVsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {


        holder.binding.root.setBackgroundResource(
            if (sparseBooleanArray.get(position))
                R.drawable.bg_rounded_card_red_stroke
            else R.drawable.bg_rounded_card
        )

        holder.itemView.setOnClickListener {
            val bool = sparseBooleanArray.get(position)
            sparseBooleanArray.append(position, !bool)
            notifyDataSetChanged()

            onSelection?.invoke(isSelected)

        }
    }

    private val isSelected: Boolean
        get() = sparseBooleanArray.containsValue(true)

    override fun getItemCount(): Int {
        return 6
    }
}