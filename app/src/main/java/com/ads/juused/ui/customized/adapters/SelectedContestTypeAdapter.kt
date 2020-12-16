package com.ads.juused.ui.customized.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.ads.juused.base.BaseRecyclerAdapter
import com.ads.juused.base.BaseViewHolder
import com.ads.juused.databinding.ItemContestTypeSelectedBinding

class SelectedContestTypeAdapter : BaseRecyclerAdapter<Any, SelectedContestTypeAdapter.SelectedContestTypeViewHolder>(
    diffUtil) {

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): SelectedContestTypeViewHolder
            = SelectedContestTypeViewHolder(ItemContestTypeSelectedBinding.inflate(inflater,parent,false))

    override fun onBindViewHolder(holder: SelectedContestTypeViewHolder, position: Int) {

    }

    inner class SelectedContestTypeViewHolder(binding: ItemContestTypeSelectedBinding): BaseViewHolder<Any>(binding) {

        override fun bindTo(data: Any) {

        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = true

            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = true
        }
    }

    override fun getItemCount(): Int = 1
}