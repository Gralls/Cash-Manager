package com.patryk.springer.shoppinglist.view.listdetails

import com.patryk.springer.shoppinglist.data.entity.Product
import com.patryk.springer.shoppinglist.data.entity.ShoppingListWithProducts
import com.patryk.springer.shoppinglist.data.repository.ProductsRepo
import com.patryk.springer.shoppinglist.data.repository.ShoppingListsRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Patryk Springer on 2019-06-15.
 */
class ListDetailsPresenter @Inject constructor(
    private val mView: ListDetailsContract.View,
    private val mListRepo: ShoppingListsRepo,
    private val mProductsRepo: ProductsRepo
) :
    ListDetailsContract.Presenter {

    private val mDisposable = CompositeDisposable()
    private var mShoppingList: ShoppingListWithProducts? = null
    private var mListId: Int = 0
    private var mSelectedProduct: Product? = null

    override fun refreshView(listId: Int) {
        mListId = listId
    }

    override fun getViewTypeOfProduct(position: Int): ProductTypeEnum? {
        val product: Product? = mShoppingList?.mProducts?.get(position)
        return when (product?.mIsChecked) {
            true -> ProductTypeEnum.CHECKED
            false -> ProductTypeEnum.UNCHECKED
            else -> null
        }
    }

    override fun getProductListSize(): Int = mShoppingList?.getProductsCount() ?: 0

    override fun onProductRowBinded(position: Int, rowView: ListDetailsContract.RowView) {
        val product: Product = mShoppingList?.getProductAtPosition(position) ?: return
        rowView.setProductName(product.mName)
        rowView.setProductQuantity(product.mQuantity)
        rowView.setProductChecked(product.mIsChecked)
    }

    override fun onAttach() {
        mDisposable +=
            mListRepo.getShoppingListDetails(mListId).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribeOn(Schedulers.io()).subscribeBy(
                onNext = { list ->
                    list.mProducts = list.mProducts.sortedBy { it.mIsChecked }
                    mShoppingList = list
                    val listName = mShoppingList?.mShoppingList?.mName ?: ""
                    mView.showListName(listName, list.isListArchived())
                    mView.showAddNewProductButton(!list.isListArchived())
                    mView.refreshList()
                })

    }

    override fun onNewProductAdded(name: String, quantity: Int) {
        mProductsRepo.createNewProduct(Product(name, mListId, quantity))
    }

    override fun onProductChecked(position: Int) {
        val product: Product = mShoppingList?.getProductAtPosition(position) ?: return
        mProductsRepo.setProductChecked(product.mId, !product.mIsChecked)
    }

    override fun onDetach() {
        mDisposable.clear()
    }

    override fun isShoppingListActive(): Boolean = !(mShoppingList?.isListArchived() ?: false)
    override fun onProductLongClicked(position: Int) {
        mSelectedProduct = mShoppingList?.getProductAtPosition(position)
        mView.showActionMenu()
    }

    override fun onProductRemoved() {
        mSelectedProduct?.let {
            mProductsRepo.deleteProduct(it.mId)

        }
    }

    override fun onProductEditClicked() {
        mView.showEditProductDialog(mSelectedProduct?.mName ?: "", mSelectedProduct?.mQuantity ?: 0)
    }

    override fun onProductEditConfirmed(name: String, quantity: Int) {
        mProductsRepo.editProduct(name, quantity, mSelectedProduct?.mId ?: 0)
    }
}