package com.packapps.core.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

fun <T> LiveData<T>.getOrAwaitValue(time: Long = 2, timeUnit: TimeUnit = TimeUnit.SECONDS): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = Observer<T> { o ->
        data = o
        latch.countDown()
    }

    observeForever(observer)

    // Espera por [time] segundos para o valor ser emitido
    if (!latch.await(time, timeUnit)) {
        throw TimeoutException("LiveData value was never set.")
    }

    removeObserver(observer)

    @Suppress("UNCHECKED_CAST")
    return data as T
}
