package com.test.testnewsapp.testnewsapp

import android.support.multidex.MultiDexApplication
import com.test.testnewsapp.testnewsapp.di.component.ApplicationComponent
import com.test.testnewsapp.testnewsapp.di.component.DaggerApplicationComponent
import com.test.testnewsapp.testnewsapp.di.module.ApplicationModule
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Inject

class BaseApplication : MultiDexApplication() {

    private val TAG = BaseApplication::class.java.simpleName

    lateinit var mApplicationComponent: ApplicationComponent
    lateinit var mApplication: BaseApplication

    @Inject
    lateinit var mCalligraphyConfig: CalligraphyConfig

    override fun onCreate() {
        super.onCreate()

        mApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()

        mApplicationComponent.inject(this)
        CalligraphyConfig.initDefault(mCalligraphyConfig)
        mApplication = this
    }

    fun getComponent(): ApplicationComponent? {
        return mApplicationComponent
    }


    fun setComponent(applicationComponent: ApplicationComponent) {
        mApplicationComponent = applicationComponent
    }

    fun getApplication(): BaseApplication? {
        return mApplication
    }
}