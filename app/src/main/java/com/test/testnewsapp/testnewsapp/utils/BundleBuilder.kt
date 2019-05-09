package com.test.testnewsapp.testnewsapp.utils

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import java.io.Serializable

class BundleBuilder private constructor() {
    private val bundle: Bundle = Bundle()

    fun build(): Bundle = bundle

    fun <T : Fragment> setAsArgumentsFor(supportFragment: T): T =
            supportFragment.apply { arguments = build() }

    fun putString(key: String, value: String): BundleBuilder = apply { bundle.putString(key, value) }

    fun putParcelable(key: String, value: Parcelable): BundleBuilder =
            apply { bundle.putParcelable(key, value) }

    fun putInt(key: String, value: Int): BundleBuilder = apply { bundle.putInt(key, value) }

    fun putLong(key: String, value: Long): BundleBuilder = apply { bundle.putLong(key, value) }

    fun putSerializable(key: String, value: Serializable?): BundleBuilder =
            apply { bundle.putSerializable(key, value) }

    companion object {
        fun builder(): BundleBuilder = BundleBuilder()
    }
}