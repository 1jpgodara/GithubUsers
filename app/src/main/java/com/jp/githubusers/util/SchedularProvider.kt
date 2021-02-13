package com.jp.githubusers.util

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class SchedularProvider {

    fun io(): Scheduler = Schedulers.io()

    private val networkIo = Executors.newFixedThreadPool(5)

    fun network(): Executor = networkIo // Added for arch components
}
