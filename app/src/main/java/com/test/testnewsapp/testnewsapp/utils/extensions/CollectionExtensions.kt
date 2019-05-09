package com.test.testnewsapp.testnewsapp.utils.extensions

//These are some extension methods that we can use :)

inline fun <reified V> Iterable<Any>.findType(): V? = this.find { it is V } as? V

fun <T, KEY : Class<out T>, VAL> Map<KEY, VAL>.getDirectOrAssignableKey(clazz: KEY): VAL? =
        get(clazz) ?: entries.find { clazz.isAssignableFrom(it.key) }?.value

