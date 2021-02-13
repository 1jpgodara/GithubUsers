package com.jp.githubusers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jp.githubusers.data.GithubUser
import com.jp.githubusers.data.GithubUserRepository
import com.jp.githubusers.data.UsersApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubUsersViewModel : ViewModel() {

    private val githubUserRepo = GithubUserRepository()

//    private val interceptor = HttpLoggingInterceptor().apply {
//        level = HttpLoggingInterceptor.Level.BASIC
//    }
//
//    private val okHttpClient = OkHttpClient.Builder()
//        .addInterceptor(ResponseInterceptor())
//        .addInterceptor(interceptor)
//        .connectTimeout(15, TimeUnit.SECONDS)
//        .readTimeout(15, TimeUnit.SECONDS)
//        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val usersApi = retrofit.create(UsersApi::class.java)

    val job = Job()
    val scope = CoroutineScope(job + Dispatchers.IO)

    private var githubUserResults: Flow<PagingData<GithubUser>>? = null

    fun getGithubUserList(): Flow<PagingData<GithubUser>> {
        val lastResult = githubUserResults
        if (lastResult != null) return lastResult
        val newResult: Flow<PagingData<GithubUser>> =
            githubUserRepo.getPaggingData(usersApi).cachedIn(scope)
        githubUserResults = newResult
        return newResult
    }

    override fun onCleared() {
        super.onCleared()
        scope.coroutineContext.cancelChildren()
    }
}
