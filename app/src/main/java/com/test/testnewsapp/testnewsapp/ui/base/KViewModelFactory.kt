package com.test.testnewsapp.testnewsapp.ui.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.test.testnewsapp.testnewsapp.utils.extensions.getDirectOrAssignableKey
import com.test.testnewsapp.testnewsapp.utils.extensions.withRuntimeExceptions
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class KViewModelFactory @Inject constructor(
    creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : GenericViewModelFactory(creators)

abstract class GenericViewModelFactory constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        withRuntimeExceptions {
            creators.getDirectOrAssignableKey(modelClass)?.get() as? T
                ?: throw IllegalArgumentException("unknown model class $modelClass")
        }
}
