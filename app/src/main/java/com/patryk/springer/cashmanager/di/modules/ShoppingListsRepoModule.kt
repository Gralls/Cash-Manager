package com.patryk.springer.cashmanager.di.modules

import com.patryk.springer.cashmanager.data.repository.ProductsDbRepo
import com.patryk.springer.cashmanager.data.repository.ProductsRepo
import com.patryk.springer.cashmanager.data.repository.ShoppingListsDbRepo
import com.patryk.springer.cashmanager.data.repository.ShoppingListsRepo
import dagger.Binds
import dagger.Module

/**
 * Created by Patryk Springer on 2019-06-14.
 */
@Module
abstract class ShoppingListsRepoModule {

    @Binds
    abstract fun bindShoppingListRepo(repo: ShoppingListsDbRepo): ShoppingListsRepo

    @Binds
    abstract fun bindProductRepo(repo: ProductsDbRepo): ProductsRepo
}