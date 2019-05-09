package com.test.testnewsapp.testnewsapp.ui.base

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import butterknife.Unbinder
import com.test.testnewsapp.testnewsapp.BaseApplication
import com.test.testnewsapp.testnewsapp.di.component.ActivityComponent
import com.test.testnewsapp.testnewsapp.di.component.DaggerActivityComponent
import com.test.testnewsapp.testnewsapp.di.module.ActivityModule
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import javax.inject.Inject


abstract class BaseActivity : AppCompatActivity(), MvpView, BaseFragment.Callback {

    private lateinit var mActivityComponent: ActivityComponent
    private var mUnBinder: Unbinder? = null

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .applicationComponent((application as BaseApplication).getComponent())
            .build()
    }

    fun getActivityComponent(): ActivityComponent {
        return mActivityComponent
    }

    override fun onError(resId: Int) {
        onError(getString(resId))
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showMessage(resId: Int) {
        showMessage(getString(resId))
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    fun setUnBinder(unBinder: Unbinder) {
        mUnBinder = unBinder
    }

    override fun onDestroy() {
        mUnBinder?.unbind()
        super.onDestroy()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }

    //Every activity will have to override this method in which activity will be setting up the view to be displayed
    protected abstract fun setUp()
}