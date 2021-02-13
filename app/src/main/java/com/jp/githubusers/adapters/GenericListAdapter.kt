package com.jp.githubusers.adapters


import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GenericListAdapter<T>(
    data: List<T>?,
    private val itemClickListener: ((Int, T?) -> Unit)? = null,
    internal val creator: (ViewGroup, Int) -> GenericViewHolder<T>,
    val viewType: ((T?) -> Int)?
) : RecyclerView.Adapter<GenericViewHolder<T>>() {

    private var data: List<T>? = null

    init {
        if (data != null) {
            this.data = data
        }
    }

    fun getItem(position: Int) = data?.get(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
        return creator(parent, viewType)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int) {
        holder.bind(data?.get(position))
        itemClickListener?.let {
            holder.itemView.setOnClickListener { it(holder.layoutPosition, data?.get(position)) }
        }
    }

    override fun getItemCount(): Int {
        return if (data != null) {
            data!!.size
        } else 0
    }

    fun setData(newData: ArrayList<T>?) {
        if (newData != null) {
            data = newData
            notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (viewType != null) return viewType.invoke(data?.get(position))
        return super.getItemViewType(position)
    }
}
