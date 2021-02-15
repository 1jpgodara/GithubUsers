package com.jp.githubusers.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jp.githubusers.adapters.GenericViewHolder
import com.jp.githubusers.data.GithubUser
import com.jp.githubusers.databinding.ItemGithubUserListBinding

class GithubUserListItemVh(
    private val viewDataBinding: ItemGithubUserListBinding
) : GenericViewHolder<GithubUser?>(viewDataBinding.root) {

    constructor(parent: ViewGroup) : this(
        ItemGithubUserListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun bind(data: GithubUser?) {
        viewDataBinding.data = data
        viewDataBinding.executePendingBindings()
    }
}
