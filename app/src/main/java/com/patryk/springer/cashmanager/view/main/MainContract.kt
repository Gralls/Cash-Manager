package com.patryk.springer.cashmanager.view.main

import com.patryk.springer.cashmanager.view.base.BaseContract

/**
 * Created by Patryk Springer on 2019-06-14.
 */
interface MainContract {

    interface View : BaseContract.View<Presenter>

    interface Presenter : BaseContract.Presenter
}