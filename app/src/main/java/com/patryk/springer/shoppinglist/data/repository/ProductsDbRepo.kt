package com.patryk.springer.shoppinglist.data.repository

import com.patryk.springer.shoppinglist.data.dao.ProductDao
import com.patryk.springer.shoppinglist.data.entity.Product
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Patryk Springer on 2019-06-14.
 */

@Singleton
class ProductsDbRepo @Inject constructor(private val mDao: ProductDao) : ProductsRepo {

	override fun getAllProducts(): Flowable<List<Product>> = mDao.getAllProducts()

	override fun createNewProduct(product: Product) {
		Completable.fromAction { mDao.insertNewProduct(product) }.subscribeOn(Schedulers.io())
				.observeOn(Schedulers.io()).subscribeBy()
	}

	override fun getProductsInList(listId: Int): Flowable<List<Product>> =
		mDao.getProductsInList(listId)

	override fun setProductChecked(productId: Int, isChecked: Boolean) {
		Completable.fromAction { mDao.updateProductCheckedStatus(productId, isChecked) }
				.subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribeBy()
	}
}