package com.jp.githubusers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jp.githubusers.data.GithubUser
import com.jp.githubusers.databinding.ItemGithubUserListBinding

class GithubUserListItemViewHolder(
    private val viewDataBinding: ItemGithubUserListBinding
) : RecyclerView.ViewHolder(viewDataBinding.root) {

    fun bindTo(githubUser: GithubUser?) {
        viewDataBinding.data = githubUser
        viewDataBinding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup, layoutId: Int) =
            GithubUserListItemViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    layoutId, parent, false
                )
            )
    }
}
