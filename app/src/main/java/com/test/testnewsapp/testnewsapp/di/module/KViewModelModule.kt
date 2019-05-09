package com.test.testnewsapp.testnewsapp.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.test.testnewsapp.testnewsapp.di.ViewModelKey
import com.test.testnewsapp.testnewsapp.ui.base.KViewModelFactory
import com.test.testnewsapp.testnewsapp.vm.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class KViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: KViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewViewModel(model: MainViewModel): ViewModel
}
