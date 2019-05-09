package com.test.testnewsapp.testnewsapp.ui.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class RecyclableCompositeDispoable @Inject constructor() {

    var compDisposable = CompositeDisposable()

    fun add(d: Disposable) = compDisposable.add(d)

    fun disposeAndReset() {
        compDisposable.let {
            compDisposable = CompositeDisposable()
            it.dispose()
        }
    }
}
