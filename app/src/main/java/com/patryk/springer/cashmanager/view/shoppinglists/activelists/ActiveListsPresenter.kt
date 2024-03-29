package com.patryk.springer.cashmanager.view.shoppinglists.activelists

import com.patryk.springer.cashmanager.data.entity.ShoppingList
import com.patryk.springer.cashmanager.data.repository.ShoppingListsRepo
import com.patryk.springer.cashmanager.view.shoppinglists.BaseListPresenter
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

    override fun onShoppingListEdit() {
        mSelectedShoppingList?.let { list ->
            mView.showEditListDialog(list.mName)
        }
    }

    override fun onShoppingListEditSaved(newName: String) {
        mSelectedShoppingList?.let { list ->
            mListsRepo.setShoppingListName(list.mId, newName)
        }
    }
}