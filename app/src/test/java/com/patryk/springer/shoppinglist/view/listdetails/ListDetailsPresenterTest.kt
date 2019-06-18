package com.patryk.springer.shoppinglist.view.listdetails

import com.nhaarman.mockitokotlin2.*
import com.patryk.springer.shoppinglist.BaseTest
import com.patryk.springer.shoppinglist.data.entity.Product
import com.patryk.springer.shoppinglist.data.entity.ShoppingList
import com.patryk.springer.shoppinglist.data.entity.ShoppingListWithProducts
import com.patryk.springer.shoppinglist.data.repository.ProductsRepo
import com.patryk.springer.shoppinglist.data.repository.ShoppingListsRepo
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test

/**
 * Created by Patryk Springer on 2019-06-17.
 */
class ListDetailsPresenterTest : BaseTest() {


    private val mView = mock<ListDetailsContract.View>()
    private val mListRepo = mock<ShoppingListsRepo>()
    private val mProductRepo = mock<ProductsRepo>()

    private lateinit var mPresenter: ListDetailsContract.Presenter

    private lateinit var mShoppingItem: Product
    private lateinit var mActiveShoppingList: ShoppingListWithProducts
    private lateinit var mArchivedShoppingList: ShoppingListWithProducts
    private lateinit var mProductsList: List<Product>

    @Before
    fun prepareTest() {
        mPresenter = ListDetailsPresenter(mView, mListRepo, mProductRepo)
        prepareValues()
    }

    private fun prepareValues() {
        mShoppingItem = Product("Mock prod", 1)
        mProductsList = listOf(
            Product("Mock prod1", 1),
            Product("Mock prod2", 2),
            Product("Mock prod 3", 3)
        )
        mActiveShoppingList = ShoppingListWithProducts().apply {
            mShoppingList = ShoppingList("Active list", mId = 1)
            mProducts = mProductsList.filter { it.mShoppingListId == mShoppingList?.mId }
        }
        mArchivedShoppingList = ShoppingListWithProducts().apply {
            mShoppingList = ShoppingList("Archived list", true, mId = 2)
            mProducts = mProductsList.filter { it.mShoppingListId == mShoppingList?.mId }
        }
    }

    private fun prepareActiveListView() {
        val listId: Int = mActiveShoppingList.mShoppingList?.mId!!
        mListRepo.stub {
            on {
                getShoppingListDetails(listId)
            } doReturn Flowable.just(mActiveShoppingList)
        }
        mPresenter.refreshView(listId)
        mPresenter.onAttach()
    }

    private fun getFirstProductOfList(list: ShoppingListWithProducts): Product =
        mProductsList.first { it.mShoppingListId == list.mShoppingList?.mId!! }

    private fun prepareArchiveListView() {
        val listId: Int = mArchivedShoppingList.mShoppingList?.mId!!
        mListRepo.stub {
            on {
                getShoppingListDetails(listId)
            } doReturn Flowable.just(mArchivedShoppingList)
        }
        mPresenter.refreshView(listId)
        mPresenter.onAttach()
    }

    @Test
    fun testViewRefreshWithActiveList() {
        prepareActiveListView()
        verify(mView).refreshList()
    }

    @Test
    fun testActiveListProductLongClicked() {
        prepareActiveListView()
        mPresenter.onProductLongClicked(0)
        verify(mView).showActionMenu()
    }

    @Test
    fun testArchivedListProductLongClickedDoNothing() {
        prepareArchiveListView()
        mPresenter.onProductLongClicked(0)
        verify(mView, never()).showActionMenu()
    }

    @Test
    fun testActiveListChangeProductCheckStatus() {
        prepareActiveListView()
        val productBeforeChange = getFirstProductOfList(mActiveShoppingList)
        mPresenter.onProductChecked(0)
        verify(mProductRepo).setProductChecked(
            productBeforeChange.mId,
            !productBeforeChange.mIsChecked
        )
    }

    @Test
    fun testArchivedListChangeProductCheckStatus() {
        prepareArchiveListView()
        val product = getFirstProductOfList(mArchivedShoppingList)
        mPresenter.onProductChecked(0)
        verify(mProductRepo, never()).setProductChecked(product.mId, !product.mIsChecked)
    }

    @Test
    fun testNewProductAddedCalledRepo() {
        prepareActiveListView()
        mPresenter.onNewProductAdded("New prod", 2)
        val product = Product("New prod", mActiveShoppingList.mShoppingList?.mId!!, 2)
        verify(mProductRepo).createNewProduct(product)
    }

    @Test
    fun testProperProductIsEditing() {
        prepareActiveListView()
        val product = getFirstProductOfList(mActiveShoppingList)
        mPresenter.onProductLongClicked(0)
        mPresenter.onProductEditClicked()
        verify(mView).showEditProductDialog(product.mName, product.mQuantity)
    }

}