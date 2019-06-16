package com.patryk.springer.shoppinglist.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Patryk Springer on 2019-06-14.
 */
@Entity
data class Product(
    @ColumnInfo(name = "name")
    val mName: String,
    @ColumnInfo(name = "shoppingListId")
    val mShoppingListId: Int,
    @ColumnInfo(name = "quantity")
    val mQuantity: Int = 0,
    @ColumnInfo(name = "isChecked")
    val mIsChecked: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val mId: Int = 0
)