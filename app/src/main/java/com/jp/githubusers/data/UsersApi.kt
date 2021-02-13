package com.jp.githubusers.data

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {
    @GET("/users")
    suspend fun getUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int = 20
    ): Response<List<GithubUser>>
}
