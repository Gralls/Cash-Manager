package com.patryk.springer.shoppinglist.data.repository

import com.patryk.springer.shoppinglist.data.dao.ShoppingListsDao
import com.patryk.springer.shoppinglist.data.entity.ShoppingList
import com.patryk.springer.shoppinglist.data.entity.ShoppingListWithProducts
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Patryk Springer on 2019-06-14.
 */
@Singleton
class ShoppingListsDbRepo @Inject constructor(val mDao: ShoppingListsDao) : ShoppingListsRepo {

    override fun getActiveShoppingLists(): Flowable<List<ShoppingList>> =
        mDao.getActiveShoppingLists()

    override fun getShoppingListsWithProducts(
        isArchived: Boolean
    ): Flowable<List<ShoppingListWithProducts>> {
        return mDao.getShoppingListsWithProducts(isArchived)
    }

    override fun createNewShoppingList(shoppingList: ShoppingList) {
        Completable.fromAction { mDao.createNewShoppingList(shoppingList) }
            .subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribeBy()
    }

    override fun setShoppingListArchivedStatus(listId: Int, isArchived: Boolean) {
        Completable.fromAction { mDao.setShoppingListArchivedStatus(listId, isArchived) }
            .subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribeBy()
    }

    override fun removeShoppingList(listId: Int) {
        Completable.fromAction { mDao.removeShoppingList(listId) }.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io()).subscribeBy()
    }

    override fun getShoppingListDetails(listId: Int): Flowable<ShoppingListWithProducts> =
        mDao.getShoppingListDetails(listId)
}