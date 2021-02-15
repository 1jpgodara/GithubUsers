package com.jp.githubusers.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jp.githubusers.R
import com.jp.githubusers.adapters.GenericListAdapter
import com.jp.githubusers.data.GithubUser
import com.jp.githubusers.databinding.GithubUserDetailFragmentBinding
import com.jp.githubusers.util.visible

class GithubUserDetailFragment : Fragment() {

    companion object {
        private const val EXTRA_USERS_DATA = "extra_users_data"

        fun newInstance(users: List<GithubUser?>): GithubUserDetailFragment {


            val newFragment = GithubUserDetailFragment()
            val arrayList = ArrayList<GithubUser?>()
            users.forEach {
                arrayList.add(it)
            }
            newFragment.arguments = Bundle().apply {
                putParcelableArrayList(EXTRA_USERS_DATA, arrayList)
            }
            return newFragment
        }
    }

    private lateinit var binding: GithubUserDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.github_user_detail_fragment,
            container,
            false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val users = arguments?.getParcelableArray(EXTRA_USERS_DATA) as ArrayList<GithubUser?>

        val listAdapter = GenericListAdapter(
            users,
            { _, _ -> },
            { viewGroup, _ ->
                GithubUserDetailItemVh(
                    viewGroup
                )
            },
            null
        )

        with(binding.listGithubUsers) {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
            visible()
        }
    }
}
