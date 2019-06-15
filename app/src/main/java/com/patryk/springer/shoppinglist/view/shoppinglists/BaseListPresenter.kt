package com.patryk.springer.shoppinglist.view.shoppinglists

import com.patryk.springer.shoppinglist.data.entity.ShoppingList
import com.patryk.springer.shoppinglist.data.entity.ShoppingListWithProducts
import com.patryk.springer.shoppinglist.data.repository.ShoppingListsRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat

/**
 * Created by Patryk Springer on 2019-06-15.
 */
abstract class BaseListPresenter constructor(private val mView: BaseListContract.View<*>,
											 private val mListsRepo: ShoppingListsRepo) :
		BaseListContract.Presenter {

	protected var mSelectedShoppingList: ShoppingList? = null
	private var mShoppingList: List<ShoppingListWithProducts> = emptyList()
	private val mDisposable = CompositeDisposable()
	private val mDateFormatter: SimpleDateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")

	abstract val mIsArchivedList: Boolean

	override fun onAttach() {
		mDisposable.add(mListsRepo.getShoppingListsWithProducts(mIsArchivedList).observeOn(
				AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeBy(
				onNext = { lists ->
					mShoppingList = lists
					mView.updateShoppingLists()
				}))
	}

	override fun onShoppingListBind(position: Int, rowView: BaseListContract.RowView) {
		val shoppingList = mShoppingList[position]
		rowView.showListName(shoppingList.mShoppingList?.mName ?: "")
		rowView.showProductsCount(shoppingList.getCheckedProductsCount(),
				shoppingList.getProductsCount())
		val createdDate: String = mDateFormatter.format(shoppingList.mShoppingList?.mDate)
		rowView.showCreatedDate(createdDate)
	}

	override fun onDetach() {
		mDisposable.clear()
	}

	override fun onShoppingListLongClicked(position: Int) {
		mSelectedShoppingList = mShoppingList[position].mShoppingList
		mView.showContextMenu()
	}

	override fun onShoppingListRemoved() {
		val listId = mSelectedShoppingList?.mId ?: return
		mListsRepo.removeShoppingList(listId)
		mSelectedShoppingList = null
	}

	override fun getShoppingListSize(): Int = mShoppingList.size
}