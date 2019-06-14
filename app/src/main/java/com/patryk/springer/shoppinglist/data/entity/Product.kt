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
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val mId: Int = 0

)