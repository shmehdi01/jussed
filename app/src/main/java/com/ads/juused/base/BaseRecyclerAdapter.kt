package com.ads.juused.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<Data, VH : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<Data>
) : ListAdapter<Data, VH>(diffCallback) {

    protected var onClick : ((Data?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return onCreateViewHolder(
            inflater = LayoutInflater.from(parent.context),
            parent = parent,
            viewType = viewType
        )
    }

    /**
     *  TODO Remove while api integration
     */
    override fun getItemCount(): Int = 10

    fun setOnItemClickListener(onClick: (Data?) -> Unit) {
        this.onClick = onClick
    }

    abstract fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): VH
}