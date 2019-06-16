package com.patryk.springer.shoppinglist.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.patryk.springer.shoppinglist.data.entity.Product
import io.reactivex.Flowable

/**
 * Created by Patryk Springer on 2019-06-14.
 */
@Dao
abstract class ProductDao {

	@Insert
	abstract fun insertNewProduct(product: Product)

	@Query("SELECT * FROM product")
	abstract fun getAllProducts(): Flowable<List<Product>>

	@Query("SELECT * FROM product WHERE shoppingListId = :listId")
	abstract fun getProductsInList(listId: Int): Flowable<List<Product>>

	@Query("UPDATE product SET isChecked = :isChecked WHERE id = :productId ")
	abstract fun updateProductCheckedStatus(productId: Int, isChecked: Boolean)
}