package com.test.testnewsapp.testnewsapp.ui.base

import android.support.annotation.StringRes

public interface MvpView {
    fun onError(@StringRes resId: Int)

    fun onError(message: String)

    fun showMessage(message: String)

    fun showMessage(@StringRes resId: Int)

    fun showLoading()

    fun hideLoading()
}
