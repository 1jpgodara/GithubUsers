package com.jp.githubusers.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


inline fun <reified T> moshiAdapter(): JsonAdapter<T> =
    Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(T::class.java)

inline fun <reified T> T.toJson(): String = moshiAdapter<T>().toJson(this)

inline fun <reified T> String.fromJson(): T? = try {
    moshiAdapter<T>().fromJson(this)
} catch (e: Exception) {
    null
}
