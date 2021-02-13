package com.jp.githubusers.data

import androidx.paging.PagingSource
import retrofit2.HttpException
import java.io.IOException

class UserSource(private val githubUserApi: UsersApi) : PagingSource<Int, GithubUser>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubUser> {
        return try {
            val response = githubUserApi.getUsers(params.key ?: 0)
            val results = response.body() ?: ArrayList()

            LoadResult.Page(
                data = results,
                prevKey = params.key,
                nextKey = results.size
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}
