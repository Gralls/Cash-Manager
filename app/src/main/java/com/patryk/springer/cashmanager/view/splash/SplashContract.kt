package com.patryk.springer.cashmanager.view.splash

import com.patryk.springer.cashmanager.view.base.BaseContract

/**
 * Created by Patryk Springer on 2019-07-10.
 */
interface SplashContract {
    interface View : BaseContract.View<Presenter> {
        fun openMainView()
        fun openLoginView()
    }

    interface Presenter : BaseContract.Presenter
}