package com.patryk.springer.shoppinglist.data.repository

import com.patryk.springer.shoppinglist.data.entity.Product
import io.reactivex.Flowable

/**
 * Created by Patryk Springer on 2019-06-14.
 */

interface ProductsRepo {

	fun getAllProducts(): Flowable<List<Product>>
	fun createNewProduct(product: Product)
	fun getProductsInList(listId: Int): Flowable<List<Product>>
	fun setProductChecked(productId: Int, isChecked: Boolean)
	fun deleteProduct(productId: Int)
	fun editProduct(name: String, quantity: Int, productId: Int)
}