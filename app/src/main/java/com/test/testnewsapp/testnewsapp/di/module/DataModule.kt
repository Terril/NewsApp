package com.test.testnewsapp.testnewsapp.di.module

import com.test.testnewsapp.testnewsapp.data.AppDataManager
import com.test.testnewsapp.testnewsapp.data.DataManager
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindEventDataRepo(impl: AppDataManager): DataManager

}
