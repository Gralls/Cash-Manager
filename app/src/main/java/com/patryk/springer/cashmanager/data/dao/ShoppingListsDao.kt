package com.patryk.springer.cashmanager.data.dao

import androidx.room.*
import com.patryk.springer.cashmanager.data.converters.DateConverter
import com.patryk.springer.cashmanager.data.entity.ShoppingList
import com.patryk.springer.cashmanager.data.entity.ShoppingListWithProducts
import io.reactivex.Flowable

/**
 * Created by Patryk Springer on 2019-06-14.
 */
@Dao
@TypeConverters(DateConverter::class)
abstract class ShoppingListsDao {

    @Insert
    abstract fun createNewShoppingList(shoppingList: ShoppingList)

    @Query("SELECT * FROM shoppinglist WHERE isArchived = 0")
    abstract fun getActiveShoppingLists(): Flowable<List<ShoppingList>>

    @Transaction
    @Query("SELECT * FROM shoppinglist WHERE isArchived = :isArchived ORDER BY date DESC")
    abstract fun getShoppingListsWithProducts(
        isArchived: Boolean
    ): Flowable<List<ShoppingListWithProducts>>

    @Query("UPDATE shoppinglist SET isArchived = :isArchived WHERE id = :listId")
    abstract fun setShoppingListArchivedStatus(listId: Int, isArchived: Boolean)

    @Query("UPDATE shoppinglist SET name = :listName WHERE id = :listId")
    abstract fun setShoppingListName(listId: Int, listName: String)

    @Query("DELETE FROM shoppinglist WHERE id = :listId ")
    abstract fun removeShoppingList(listId: Int)

    @Transaction
    @Query("SELECT * FROM shoppinglist WHERE id = :listId")
    abstract fun getShoppingListDetails(listId: Int): Flowable<ShoppingListWithProducts>
}