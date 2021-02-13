package com.jp.githubusers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jp.githubusers.view.UserListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        openUserList()
    }

    private fun openUserList() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, UserListFragment.newInstance())
            .commitNow()
    }

//    fun openUserDetail(inputData: InputData) {
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.container, UserDetailFragment.newInstance(inputData))
//            .commitNow()
//    }
}