package com.patryk.springer.shoppinglist

import androidx.annotation.CallSuper
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before

/**
 * Created by Patryk Springer on 2019-06-17.
 */
abstract class BaseTest {

    @CallSuper
    @Before
    fun prepareRxSchedulers() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setSingleSchedulerHandler { Schedulers.trampoline() }
    }

    @CallSuper
    @After
    fun resetRxSchedulers() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

}