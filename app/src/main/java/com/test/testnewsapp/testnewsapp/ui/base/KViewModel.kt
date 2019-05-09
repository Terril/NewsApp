package com.test.testnewsapp.testnewsapp.ui.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import io.reactivex.disposables.Disposable

inline fun <reified T : ViewModel> ViewModelProvider.getViewModel(): T = get(T::class.java)

inline fun <reified T : ViewModel> ViewModelProvider.Factory.getViewModel(activity: FragmentActivity): T =
    ViewModelProviders.of(activity, this).get(T::class.java)

abstract class KViewModel : ViewModel() {

    val onClearedDisposer = RecyclableCompositeDispoable()

    /**
     * Add the provided disposable to list to be disposed when viewmodel is cleared
     */
    fun disposeOnClear(d: Disposable) {
        onClearedDisposer.add(d)
    }

    fun Disposable.disposeOnCleared() {
        onClearedDisposer.add(this)
    }

    override fun onCleared() {
        onClearedDisposer.disposeAndReset()
        super.onCleared()
    }
}
