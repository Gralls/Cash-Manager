package com.patryk.springer.shoppinglist.view.listdetails

import com.patryk.springer.shoppinglist.view.base.BaseContract

/**
 * Created by Patryk Springer on 2019-06-15.
 */
interface ListDetailsContract {

	interface View : BaseContract.View<Presenter> {
		fun showListName(name: String, isArchived: Boolean)
		fun showAddNewProductButton(isVisible: Boolean)
		fun refreshList()
	}

	interface Presenter : BaseContract.Presenter {
		fun refreshView(listId: Int)
		fun getViewTypeOfProduct(position: Int): ProductTypeEnum?
		fun getProductListSize(): Int
		fun onProductRowBinded(position: Int, rowView: RowView)
		fun onNewProductAdded(name: String, quantity: Int)
		fun onProductChecked(position: Int)
	}

	interface RowView {
		fun setProductName(name: String)
		fun setProductQuantity(quantity: Int)
		fun setProductChecked(isChecked: Boolean)
	}
}