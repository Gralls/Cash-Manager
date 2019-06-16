package com.patryk.springer.shoppinglist.view.shoppinglists.archievedlists

import com.patryk.springer.shoppinglist.view.shoppinglists.BaseListContract

/**
 * Created by Patryk Springer on 2019-06-14.
 */
interface ArchivedListsContract {

    interface View : BaseListContract.View<Presenter>

    interface Presenter : BaseListContract.Presenter {
        fun onShoppingListUnarchived()
    }
}