package com.jp.githubusers.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class GithubUserRepository {

    fun getPaggingData(githubUsersApi: UsersApi): Flow<PagingData<GithubUser>> {
        return Pager(
            config = PagingConfig(
                pageSize = 2,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UserSource(githubUsersApi) }
        ).flow
    }
}
