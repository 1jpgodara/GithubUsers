package com.jp.githubusers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jp.githubusers.data.GithubUser
import com.jp.githubusers.util.commitTransaction
import com.jp.githubusers.view.GithubUserDetailFragment
import com.jp.githubusers.view.GithubUsersListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openUserList()
    }

    private fun openUserList() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, GithubUsersListFragment.newInstance())
            .commitNow()
    }

    fun openUserDetail(users: List<GithubUser?>) {
        supportFragmentManager.commitTransaction {
            replace(R.id.container, GithubUserDetailFragment.newInstance(users))
        }
    }
}
