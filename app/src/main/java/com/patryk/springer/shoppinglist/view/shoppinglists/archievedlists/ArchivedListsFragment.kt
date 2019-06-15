package com.patryk.springer.shoppinglist.view.shoppinglists.archievedlists

import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.patryk.springer.shoppinglist.R
import com.patryk.springer.shoppinglist.view.shoppinglists.BaseListFragment
import kotlinx.android.synthetic.main.fragment_shopping_lists.*
import javax.inject.Inject

/**
 * Created by Patryk Springer on 2019-06-13.
 */
class ArchivedListsFragment : BaseListFragment<ArchivedListsContract.Presenter>(),
		ArchivedListsContract.View {


	@Inject
	override lateinit var mPresenter: ArchivedListsContract.Presenter

	override val mFragmentTitle: Int
		get() = R.string.title_archived_lists

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		fab_active_shopping_list_create.hide()
	}

	override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
		return when (item?.itemId) {
			R.id.menu_delete -> {
				mPresenter.onShoppingListRemoved()
				mode?.finish()
				true
			}
			else -> false
		}
	}

	override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
		val inflater = mode?.menuInflater
		inflater?.inflate(R.menu.archived_list_menu, menu)
		return true
	}
}