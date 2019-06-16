package com.patryk.springer.shoppinglist.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverters
import com.patryk.springer.shoppinglist.data.converters.DateConverter
import com.patryk.springer.shoppinglist.data.entity.ShoppingList
import com.patryk.springer.shoppinglist.data.entity.ShoppingListWithProducts
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

	@Query("SELECT * FROM shoppinglist WHERE isArchived = :isArchived ORDER BY date")
	abstract fun getShoppingListsWithProducts(
			isArchived: Boolean): Flowable<List<ShoppingListWithProducts>>

	@Query("UPDATE shoppinglist SET isArchived = :isArchived WHERE id = :listId")
	abstract fun setShoppingListArchivedStatus(listId: Int, isArchived: Boolean)

	@Query("DELETE FROM shoppinglist WHERE id = :listId ")
	abstract fun removeShoppingList(listId: Int)

	@Query("SELECT * FROM shoppinglist WHERE id = :listId")
	abstract fun getShoppingListDetails(listId: Int): Flowable<ShoppingListWithProducts>
}