package com.jp.githubusers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jp.githubusers.data.GithubUser
import com.jp.githubusers.databinding.ItemGithubUserDetailBinding

class UserDetailsViewHolder(
    private val binding: ItemGithubUserDetailBinding
) : GenericViewHolder<GithubUser?>(binding.root) {

    constructor(parent: ViewGroup) : this(
        ItemGithubUserDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun bind(data: GithubUser?) {
        data?.let { place ->
            binding.data = place
            binding.executePendingBindings()
        }
    }
}
