package com.jp.githubusers.util


import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.isNetConnected(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return !(networkInfo == null || !networkInfo.isConnectedOrConnecting)
}

fun Context.showShortToast(@StringRes resId: Int) =
    Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()

fun Context.showShortToast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
