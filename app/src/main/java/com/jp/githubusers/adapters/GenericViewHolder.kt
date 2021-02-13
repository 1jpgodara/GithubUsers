package com.jp.githubusers.adapters


import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class GenericViewHolder<U> protected constructor(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    /**
     * To bind data to view only
     */
    abstract fun bind(data: U?)
}
