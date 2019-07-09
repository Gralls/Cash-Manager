package com.patryk.springer.cashmanager.di.modules

import android.content.Context
import androidx.room.Room
import com.patryk.springer.cashmanager.data.ShoppingListsDb
import com.patryk.springer.cashmanager.data.dao.ProductDao
import com.patryk.springer.cashmanager.data.dao.ShoppingListsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Patryk Springer on 2019-06-14.
 */
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(context: Context): ShoppingListsDb {
        return Room.databaseBuilder(context, ShoppingListsDb::class.java, "shopping-list.db")
            .build()
    }

    @Provides
    @Singleton
    fun providesShoppingListDao(db: ShoppingListsDb): ShoppingListsDao = db.shoppingListsDao()

    @Provides
    @Singleton
    fun providesProductsDao(db: ShoppingListsDb): ProductDao = db.productsDao()
}