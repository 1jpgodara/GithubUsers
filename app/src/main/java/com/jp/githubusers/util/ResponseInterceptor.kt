package com.jp.githubusers.util

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ResponseInterceptor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val request = builder.build()
        return chain.proceed(request)
    }
}
