package com.patryk.springer.shoppinglist.view.listdetails

/**
 * Created by Patryk Springer on 2019-06-16.
 */
enum class ProductTypeEnum(val viewType: Int) {

    CHECKED(0), UNCHECKED(1);

    companion object {
        fun getProductTypeByViewType(viewType: Int): ProductTypeEnum =
            values().find { it.viewType == viewType } ?: CHECKED
    }
}