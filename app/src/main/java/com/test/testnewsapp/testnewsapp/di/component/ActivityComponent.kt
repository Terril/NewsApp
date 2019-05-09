package com.test.testnewsapp.testnewsapp.di.component

import com.test.testnewsapp.testnewsapp.di.PerActivity
import com.test.testnewsapp.testnewsapp.di.module.ActivityModule
import com.test.testnewsapp.testnewsapp.ui.detail.DetailActivity
import com.test.testnewsapp.testnewsapp.ui.main.MainActivity
import dagger.Component

@PerActivity
@Component(dependencies = [(ApplicationComponent::class)], modules = [(ActivityModule::class)])
interface ActivityComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
}
