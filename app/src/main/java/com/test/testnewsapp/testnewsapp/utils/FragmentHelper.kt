package com.test.testnewsapp.testnewsapp.utils

import android.app.Activity
import android.support.v4.app.Fragment

object FragmentHelper {

    /**
     * If obj implements interface clz then return obj cast to clz otherwise return null
     */
    @JvmStatic
    fun <T> optionalInterface(obj: Fragment, clz: Class<T>): T? = castOrNull(obj, clz)

    /**
     * If obj implements interface clz then return obj cast to clz otherwise throw ClassCastException
     */
    @JvmStatic
    fun <T> requireInterface(obj: Fragment, clz: Class<T>): T = castOrDie(obj, clz)

    /**
     * If obj implements interface clz then return obj cast to clz otherwise return null
     */
    @JvmStatic
    fun <T> optionalInterface(obj: Activity, clz: Class<T>): T? = castOrNull(obj, clz)

    /**
     * If obj implements interface clz then return obj cast to clz otherwise throw ClassCastException
     */
    @JvmStatic
    fun <T> requireInterface(obj: Activity, clz: Class<T>): T = castOrDie(obj, clz)

    /**
     * If obj implements interface clz then return obj cast to clz otherwise return null
     */
    @JvmStatic
    fun <T> optionalHostInterface(fragment: Fragment, clz: Class<T>): T? {
        castOrNull(fragment.targetFragment, clz)?.let { return it }
        castOrNull(fragment.parentFragment, clz)?.let { return it }
        return castOrNull(fragment.activity, clz)
    }

    /**
     * If obj implements interface clz then return obj cast to clz otherwise throw ClassCastException
     */
    @JvmStatic
    fun <T> requireHostInterface(obj: Fragment, clz: Class<T>): T {
        optionalHostInterface(obj, clz)?.let { return it }
        throw ClassCastException("${obj.toString()} must have target, parent or host activity implement ${clz.canonicalName}")
    }

    private fun <T> castOrNull(obj: Any?, clz: Class<T>): T? {
        return if (clz.isInstance(obj)) { clz.cast(obj) } else { null }
    }

    private fun <T> castOrDie(obj: Any?, clz: Class<T>) : T {
        castOrNull(obj, clz)?.let { return it }
        throw ClassCastException("${obj.toString()} must implement ${clz.canonicalName}")
    }


}

inline fun <reified T> Activity.optionalInterface(): T? =
        FragmentHelper.optionalInterface(this, T::class.java)

inline fun <reified T> Fragment.optionalInterface(): T? =
        FragmentHelper.optionalInterface(this, T::class.java)

inline fun <reified T> Activity.requireInterface(): T =
        FragmentHelper.requireInterface(this, T::class.java)

inline fun <reified T> Fragment.requireInterface(): T =
        FragmentHelper.requireInterface(this, T::class.java)

inline fun <reified T> Fragment.optionalHostInterface(): T? =
        FragmentHelper.optionalHostInterface(this, T::class.java)

inline fun <reified T> Fragment.requireHostInterface(): T =
        FragmentHelper.requireHostInterface(this, T::class.java)

