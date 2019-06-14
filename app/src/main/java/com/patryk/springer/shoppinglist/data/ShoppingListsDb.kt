package com.patryk.springer.shoppinglist.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.patryk.springer.shoppinglist.data.dao.ShoppingListsDao
import com.patryk.springer.shoppinglist.data.entity.Product

/**
 * Created by Patryk Springer on 2019-06-14.
 */
@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = false
)
abstract class ShoppingListsDb : RoomDatabase() {

    abstract fun shoppingListsDao(): ShoppingListsDao
}