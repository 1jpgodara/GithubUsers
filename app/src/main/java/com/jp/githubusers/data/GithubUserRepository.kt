package com.jp.githubusers.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubUserRepository {

    fun getPaggingData(): Flow<PagingData<GithubUser>> {
        return Pager(
            config = PagingConfig(
                pageSize = 2,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UserSource(getClient()) }
        ).flow
    }

    private fun getClient(): UsersApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(UsersApi::class.java)
    }
}
