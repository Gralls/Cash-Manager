package com.patryk.springer.cashmanager.data.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Created by Patryk Springer on 2019-06-15.
 */
class ShoppingListWithProducts {

    @Embedded
    var mShoppingList: ShoppingList? = null

    @Relation(parentColumn = "id", entityColumn = "shoppingListId")
    var mProducts: List<Product> = listOf()

    fun isListArchived(): Boolean = mShoppingList?.mIsArchived ?: false
    fun getCheckedProductsCount(): Int = mProducts.count { it.mIsChecked }
    fun getProductsCount(): Int = mProducts.count()
    fun getProductAtPosition(position: Int): Product = mProducts[position]
}