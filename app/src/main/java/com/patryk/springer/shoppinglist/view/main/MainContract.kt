package com.patryk.springer.shoppinglist.view.main

import com.patryk.springer.shoppinglist.view.base.BaseContract

/**
 * Created by Patryk Springer on 2019-06-14.
 */
interface MainContract {

    interface View : BaseContract.View<Presenter>

    interface Presenter : BaseContract.Presenter
}