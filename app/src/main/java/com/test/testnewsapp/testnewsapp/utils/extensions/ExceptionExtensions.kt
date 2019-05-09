package com.test.testnewsapp.testnewsapp.utils.extensions

/**
 * Run block in try-catch block and rethrow any exceptions as RuntimeException
 */
fun <T> withRuntimeExceptions(block: () -> T): T {
    try {
        return block()
    } catch (e: Exception) {
        when (e) {
            is RuntimeException -> throw e
            else                -> throw RuntimeException(e)
        }
    }
}
