package com.patryk.springer.shoppinglist.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created by Patryk Springer on 2019-06-14.
 */
@Entity
data class ShoppingList(
    @ColumnInfo(name = "name")
    val mName: String,
    @ColumnInfo(name = "isArchived")
    val mIsArchived: Boolean = false,
    @ColumnInfo(name = "date")
    val mDate: Date? = Date(),
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val mId: Int = 0
)