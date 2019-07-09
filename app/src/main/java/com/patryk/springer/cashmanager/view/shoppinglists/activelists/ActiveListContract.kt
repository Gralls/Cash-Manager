package com.patryk.springer.cashmanager.view.shoppinglists.activelists

import com.patryk.springer.cashmanager.view.shoppinglists.BaseListContract

/**
 * Created by Patryk Springer on 2019-06-13.
 */
interface ActiveListContract {

    interface View : BaseListContract.View<Presenter> {
        fun showNewListDialog()
        fun showEditListDialog(listName: String)
    }

    interface Presenter : BaseListContract.Presenter {
        fun onCreateNewListClicked()
        fun onShoppingListArchived()
        fun onShoppingListEdit()
        fun onShoppingListEditSaved(newName: String)
        fun onListSaved(listName: String)
    }
}