package com.test.testnewsapp.testnewsapp.di.component

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.test.testnewsapp.testnewsapp.BaseApplication
import com.test.testnewsapp.testnewsapp.data.DataManager
import com.test.testnewsapp.testnewsapp.di.ApplicationContext
import com.test.testnewsapp.testnewsapp.di.module.ApplicationModule
import com.test.testnewsapp.testnewsapp.di.module.KViewModelModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {

    val dataManager: DataManager
    val factory: ViewModelProvider.Factory

    fun inject(app: BaseApplication)

    @ApplicationContext
    fun context(): Context

    fun application(): Application
}