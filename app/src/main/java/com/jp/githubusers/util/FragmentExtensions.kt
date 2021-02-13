package com.jp.githubusers.util


import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


fun Fragment.isNetConnected(): Boolean {
    val connectivityManager =
        context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return !(networkInfo == null || !networkInfo.isConnectedOrConnecting)
}

fun Fragment.isNetNotConnected(): Boolean {
    return if (isNetConnected()) {
        true
    } else {
        showNoConnectivityToast()
        false
    }
}

fun Fragment.showShortToast(text: String) = context?.showShortToast(text)

fun Fragment.showShortToast(@StringRes resId: Int) = context?.showShortToast(resId)

fun Fragment.showNoConnectivityToast() = showShortToast("Please check your internet connection!!")

inline fun FragmentManager.commitTransaction(arg1: androidx.fragment.app.FragmentTransaction.() -> Unit) {
    val transaction = beginTransaction()
    arg1(transaction)
    transaction.commit()
}

