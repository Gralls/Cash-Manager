package com.patryk.springer.cashmanager.data.repository

import com.patryk.springer.cashmanager.data.entity.ShoppingList
import com.patryk.springer.cashmanager.data.entity.ShoppingListWithProducts
import io.reactivex.Flowable

/**
 * Created by Patryk Springer on 2019-06-14.
 */
interface ShoppingListsRepo {

    fun getActiveShoppingLists(): Flowable<List<ShoppingList>>
    fun createNewShoppingList(shoppingList: ShoppingList)
    fun getShoppingListsWithProducts(isArchived: Boolean): Flowable<List<ShoppingListWithProducts>>
    fun setShoppingListArchivedStatus(listId: Int, isArchived: Boolean)
    fun removeShoppingList(listId: Int)
    fun getShoppingListDetails(listId: Int): Flowable<ShoppingListWithProducts>
    fun setShoppingListName(listId: Int, listName: String)
}