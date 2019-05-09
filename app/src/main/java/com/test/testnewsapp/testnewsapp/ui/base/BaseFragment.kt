package com.test.testnewsapp.testnewsapp.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.AnimationUtils
import butterknife.Unbinder
import com.test.testnewsapp.testnewsapp.R
import com.test.testnewsapp.testnewsapp.di.component.ActivityComponent

abstract class BaseFragment : Fragment(), MvpView {

    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }

    private var mActivity: BaseActivity? = null
    private var mUnBinder: Unbinder?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp(view)
    }

    override fun onDestroy() {
        mUnBinder?.unbind()
        super.onDestroy()
    }

    override fun onError(resId: Int) {
        mActivity?.onError(resId)
    }

    override fun onError(message: String) {
        mActivity?.onError(message)
    }

    override fun showMessage(message: String) {
        mActivity?.showMessage(message)
    }

    override fun showMessage(resId: Int) {
        mActivity?.showMessage(resId)
    }

    override fun showLoading() {
        mActivity?.showLoading()
    }

    override fun hideLoading() {
        mActivity?.hideLoading()
    }

    protected abstract fun setUp(view: View)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.mActivity = activity
            activity!!.onFragmentAttached()
        }
    }

    fun getActivityComponent(): ActivityComponent? {
        return if (mActivity != null) {
            mActivity!!.getActivityComponent()
        } else null
    }

    fun getBaseActivity(): BaseActivity {
        return mActivity!!
    }

    fun setUnBinder(unBinder: Unbinder) {
        mUnBinder = unBinder
    }
}