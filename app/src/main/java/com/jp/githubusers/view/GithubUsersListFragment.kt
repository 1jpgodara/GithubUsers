package com.jp.githubusers.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jp.githubusers.MainActivity
import com.jp.githubusers.R
import com.jp.githubusers.adapters.GithubUsersPaggingAdapter
import com.jp.githubusers.data.GithubUser
import com.jp.githubusers.databinding.GithubUsersListFragmentBinding
import com.jp.githubusers.util.visible
import com.jp.githubusers.viewmodel.GithubUsersViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GithubUsersListFragment : Fragment() {

    companion object {
        fun newInstance() = GithubUsersListFragment()
    }

    private lateinit var binding: GithubUsersListFragmentBinding
    private lateinit var githubUsersViewModel: GithubUsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.github_users_list_fragment,
            container,
            false
        )
        githubUsersViewModel = ViewModelProviders.of(this)[GithubUsersViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listAdapter = GithubUsersPaggingAdapter(::onItemClick)

        with(binding.listGithubUsers) {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
            visible()
        }

        githubUsersViewModel.scope.launch {
            githubUsersViewModel.getGithubUserList().collectLatest {
                listAdapter.submitData(it)
            }

        }
    }

    private fun onItemClick(users: List<GithubUser?>) {
        (activity as MainActivity).openUserDetail(users)
    }
}
