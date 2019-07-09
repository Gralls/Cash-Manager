package com.patryk.springer.cashmanager.view.listdetails

import com.patryk.springer.cashmanager.view.base.BaseContract

/**
 * Created by Patryk Springer on 2019-06-15.
 */
interface ListDetailsContract {

    interface View : BaseContract.View<Presenter> {
        fun showListName(name: String, isArchived: Boolean)
        fun showAddNewProductButton(isVisible: Boolean)
        fun refreshList()
        fun showActionMenu()
        fun showEditProductDialog(name: String, quantity: Int)
    }

    interface Presenter : BaseContract.Presenter {
        fun refreshView(listId: Int)
        fun getViewTypeOfProduct(position: Int): ProductTypeEnum?
        fun getProductListSize(): Int
        fun onProductRowBinded(position: Int, rowView: RowView)
        fun onNewProductAdded(name: String, quantity: Int)
        fun onProductChecked(position: Int)
        fun onProductLongClicked(position: Int)
        fun isShoppingListActive(): Boolean
        fun onProductRemoved()
        fun onProductEditClicked()
        fun onProductEditConfirmed(name: String, quantity: Int)
    }

    interface RowView {
        fun setProductName(name: String)
        fun setProductQuantity(quantity: Int)
        fun setProductChecked(isChecked: Boolean)
    }
}