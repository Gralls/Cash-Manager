package com.patryk.springer.shoppinglist.view.shoppinglists.activelists

import com.patryk.springer.shoppinglist.data.entity.ShoppingList
import com.patryk.springer.shoppinglist.data.repository.ShoppingListsRepo
import com.patryk.springer.shoppinglist.view.shoppinglists.BaseListPresenter
import javax.inject.Inject

/**
 * Created by Patryk Springer on 2019-06-14.
 */
class ActiveListsPresenter @Inject constructor(
    private val mView: ActiveListContract.View,
    private val mListsRepo: ShoppingListsRepo
) :
    ActiveListContract.Presenter, BaseListPresenter(mView, mListsRepo) {

    override val mIsArchivedList: Boolean
        get() = false

    override fun onCreateNewListClicked() {
        mView.showNewListDialog()
    }

    override fun onListSaved(listName: String) {
        mListsRepo.createNewShoppingList(ShoppingList(listName))
    }

    override fun onShoppingListArchived() {
        val listId = mSelectedShoppingList?.mId ?: return
        mListsRepo.setShoppingListArchivedStatus(listId, true)
        mSelectedShoppingList = null
    }
}