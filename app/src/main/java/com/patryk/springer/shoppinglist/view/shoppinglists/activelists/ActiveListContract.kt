package com.patryk.springer.shoppinglist.view.shoppinglists.activelists

import com.patryk.springer.shoppinglist.view.shoppinglists.BaseListContract

/**
 * Created by Patryk Springer on 2019-06-13.
 */
interface ActiveListContract {

	interface View : BaseListContract.View<Presenter> {
		fun showNewListDialog()
	}

	interface Presenter : BaseListContract.Presenter {
		fun onCreateNewListClicked()
		fun onShoppingListArchived()
		fun onListSaved(listName: String)
	}
}