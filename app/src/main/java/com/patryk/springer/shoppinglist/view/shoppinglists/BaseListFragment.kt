package com.patryk.springer.shoppinglist.view.shoppinglists

import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.patryk.springer.shoppinglist.R
import com.patryk.springer.shoppinglist.view.base.BaseViewPagerFragment
import com.patryk.springer.shoppinglist.view.listdetails.ListDetailsFragment
import com.patryk.springer.shoppinglist.view.main.MainActivity
import kotlinx.android.synthetic.main.fragment_shopping_lists.*

/**
 * Created by Patryk Springer on 2019-06-15.
 */
abstract class BaseListFragment<T : BaseListContract.Presenter> : BaseViewPagerFragment<T>(),
    BaseListContract.View<T>, ActionMode.Callback {

    override val layoutResId: Int
        get() = R.layout.fragment_shopping_lists
    private val mAdapter: ShoppingListAdapter by lazy { ShoppingListAdapter(mPresenter) }
    private var mActionMode: ActionMode? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_active_shopping_lists.adapter = mAdapter
        rv_active_shopping_lists.layoutManager = LinearLayoutManager(context)
    }

    override fun updateShoppingLists() {
        mAdapter.notifyDataSetChanged()
    }

    override fun showContextMenu() {
        mActionMode = (mBaseActivity as? MainActivity)?.showActionMenu(this)
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return false
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        mActionMode = null
    }

    override fun openDetailsView(listId: Int) {
        mBaseActivity.setChildView(ListDetailsFragment.newInstance(listId))
    }
}