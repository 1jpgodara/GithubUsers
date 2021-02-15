package com.jp.githubusers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jp.githubusers.data.GithubUser
import com.jp.githubusers.data.GithubUsersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.Flow

class GithubUsersViewModel : ViewModel() {

    private val githubUserRepo = GithubUsersRepository()

    val job = Job()
    val scope = CoroutineScope(job + Dispatchers.IO)

    private var githubUserResults: Flow<PagingData<GithubUser>>? = null

    fun getGithubUserList(): Flow<PagingData<GithubUser>> {
        val lastResult = githubUserResults
        if (lastResult != null) return lastResult
        val newResult: Flow<PagingData<GithubUser>> =
            githubUserRepo.getGithubUsers().cachedIn(scope)
        githubUserResults = newResult
        return newResult
    }

    override fun onCleared() {
        super.onCleared()
        scope.coroutineContext.cancelChildren()
    }
}
