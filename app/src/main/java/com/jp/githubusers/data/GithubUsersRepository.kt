package com.jp.githubusers.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubUsersRepository {

    fun getGithubUsers(): Flow<PagingData<GithubUser>> {
        return Pager(
            config = PagingConfig(
                pageSize = 2,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GithubUsersSource(getGithubUsersClient()) }
        ).flow
    }

    private fun getGithubUsersClient(): GithubUsersApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(GithubUsersApi::class.java)
    }
}
