package com.ads.juused.ui.customized.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import com.ads.juused.R
import com.ads.juused.base.BaseRecyclerAdapter
import com.ads.juused.base.BaseViewHolder
import com.ads.juused.databinding.ItemContestSizeBinding

class ContestSizeAdapter(private val onSelected: (Int) -> Unit) :
    BaseRecyclerAdapter<Int, ContestSizeAdapter.ContestViewHolder>(diffUtil) {

    private var selectedIndex = -1

    init {
        submitList(mutableListOf(3, 4, 6, 8))
    }

    inner class ContestViewHolder(private val binding: ItemContestSizeBinding) :
        BaseViewHolder<Int>(binding) {
        override fun bindTo(data: Int) {
            binding.tvSize.text = "$data"
            setSelectionColor(adapterPosition == selectedIndex)

            binding.root.setOnClickListener {
                selectedIndex = adapterPosition
                notifyDataSetChanged()

                onSelected.invoke(data)
            }
        }

        private fun setSelectionColor(selected: Boolean) {
            binding.tvSize.setBackgroundResource(
                if (selected) R.drawable.bg_red
                else R.drawable.bg_outline_cyan
            )

            binding.tvSize.setTextColor(
                if (selected) ContextCompat.getColor(binding.root.context, R.color.colorWhite)
                else ContextCompat.getColor(binding.root.context, R.color.colorCyanAccent)
            )
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Int>() {
            override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean = oldItem == newItem

            override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ContestViewHolder =
        ContestViewHolder(ItemContestSizeBinding.inflate(inflater, parent, false))

    override fun onBindViewHolder(holder: ContestViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun getItemCount(): Int = currentList.size

    fun clearSelection() {
        selectedIndex = -1
        notifyDataSetChanged()
    }
}