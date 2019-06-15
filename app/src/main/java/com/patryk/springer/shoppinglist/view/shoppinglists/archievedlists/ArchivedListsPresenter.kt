package com.patryk.springer.shoppinglist.view.shoppinglists.archievedlists

import com.patryk.springer.shoppinglist.data.repository.ShoppingListsRepo
import com.patryk.springer.shoppinglist.view.shoppinglists.BaseListPresenter
import javax.inject.Inject

/**
 * Created by Patryk Springer on 2019-06-14.
 */
class ArchivedListsPresenter @Inject constructor(mView: ArchivedListsContract.View,
												 mListsRepo: ShoppingListsRepo) :
		ArchivedListsContract.Presenter, BaseListPresenter(mView, mListsRepo) {


	override val mIsArchivedList: Boolean
		get() = true
}