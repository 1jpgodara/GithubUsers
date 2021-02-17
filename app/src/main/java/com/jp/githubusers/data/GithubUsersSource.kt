package com.jp.githubusers.data

import androidx.paging.PagingSource
import retrofit2.HttpException
import java.io.IOException

class GithubUsersSource(
    private val githubUserApiGithub: GithubUsersApi
) : PagingSource<Int, GithubUser>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubUser> {
        return try {
            val response = githubUserApiGithub.getUsers(params.key ?: 0)
            val results = response.body() ?: ArrayList()

            LoadResult.Page(
                data = results,
                prevKey = params.key,
                nextKey = params.key?.plus(results.size) ?: results.size
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}
