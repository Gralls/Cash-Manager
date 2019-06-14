package com.patryk.springer.shoppinglist.view.activelists

import com.patryk.springer.shoppinglist.view.base.BaseContract

/**
 * Created by Patryk Springer on 2019-06-13.
 */
interface ActiveListContract {

    interface View : BaseContract.View<Presenter>

    interface Presenter : BaseContract.Presenter
}