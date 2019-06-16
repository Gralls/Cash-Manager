package com.patryk.springer.shoppinglist.view.shoppinglists

import com.patryk.springer.shoppinglist.view.base.BaseContract

/**
 * Created by Patryk Springer on 2019-06-15.
 */
interface BaseListContract {

    interface View<T : BaseContract.Presenter> : BaseContract.View<T> {
        fun updateShoppingLists()
        fun showContextMenu()
        fun openDetailsView(listId: Int)
    }

    interface Presenter : BaseContract.Presenter {
        fun getShoppingListSize(): Int
        fun onShoppingListBind(position: Int, rowView: RowView)
        fun onShoppingListLongClicked(position: Int)
        fun onShoppingListRemoved()
        fun onShoppingListClicked(position: Int)
    }

    interface RowView {
        fun showListName(name: String)
        fun showProductsCount(uncheckedCount: Int, totalCount: Int)
        fun showCreatedDate(date: String)
    }
}