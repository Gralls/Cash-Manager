package com.patryk.springer.cashmanager.view.shoppinglists.archievedlists

import com.patryk.springer.cashmanager.data.repository.ShoppingListsRepo
import com.patryk.springer.cashmanager.view.shoppinglists.BaseListPresenter
import javax.inject.Inject

/**
 * Created by Patryk Springer on 2019-06-14.
 */
class ArchivedListsPresenter @Inject constructor(
    mView: ArchivedListsContract.View,
    private val mListsRepo: ShoppingListsRepo
) :
    ArchivedListsContract.Presenter, BaseListPresenter(mView, mListsRepo) {


    override val mIsArchivedList: Boolean
        get() = true

    override fun onShoppingListUnarchived() {
        val listId = mSelectedShoppingList?.mId ?: return
        mListsRepo.setShoppingListArchivedStatus(listId, false)
        mSelectedShoppingList = null
    }
}