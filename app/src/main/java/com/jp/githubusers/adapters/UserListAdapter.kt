package com.jp.githubusers.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.jp.githubusers.R
import com.jp.githubusers.data.GithubUser

open class UserListAdapter(
    private val onItemClick: (users: List<GithubUser?>) -> Unit
) : PagingDataAdapter<GithubUser, GithubUserListItemViewHolder>(GithubUser.DIFF_CALLBACK) {

    override fun getItemViewType(position: Int) = R.layout.item_github_user_list

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GithubUserListItemViewHolder {
        return when (viewType) {
            R.layout.item_github_user_list -> GithubUserListItemViewHolder.create(parent, viewType)
                .apply {
                    this.itemView.setOnClickListener {
                        onItemClick(getClickData(adapterPosition))

                    }
                }
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: GithubUserListItemViewHolder, position: Int) {
        val githubUser = getItem(position)
        githubUser?.let {
            holder.bindTo(githubUser)
        }
    }


    private fun getClickData(position: Int): List<GithubUser?> {
        return if (position == 0) {
            listOf(null, getItem(position), getItem(position + 1))
        } else if (position == itemCount) {
            listOf(getItem(position - 1), getItem(position), null)
        } else {
            listOf(getItem(position - 1), getItem(position), getItem(position + 1))
        }
    }
}
