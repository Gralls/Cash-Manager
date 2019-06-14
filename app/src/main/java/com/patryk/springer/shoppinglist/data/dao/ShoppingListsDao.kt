package com.patryk.springer.shoppinglist.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.TypeConverters
import com.patryk.springer.shoppinglist.data.converters.DateConverter
import com.patryk.springer.shoppinglist.data.entity.Product

/**
 * Created by Patryk Springer on 2019-06-14.
 */
@Dao
@TypeConverters(DateConverter::class)
abstract class ShoppingListsDao {

    @Insert
    abstract fun insertNewProduct(product: Product)
}