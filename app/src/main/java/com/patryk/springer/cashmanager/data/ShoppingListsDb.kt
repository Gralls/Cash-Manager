package com.patryk.springer.cashmanager.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.patryk.springer.cashmanager.data.converters.DateConverter
import com.patryk.springer.cashmanager.data.dao.ProductDao
import com.patryk.springer.cashmanager.data.dao.ShoppingListsDao
import com.patryk.springer.cashmanager.data.entity.Product
import com.patryk.springer.cashmanager.data.entity.ShoppingList

/**
 * Created by Patryk Springer on 2019-06-14.
 */
@Database(entities = [Product::class, ShoppingList::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class ShoppingListsDb : RoomDatabase() {

    abstract fun shoppingListsDao(): ShoppingListsDao
    abstract fun productsDao(): ProductDao
}