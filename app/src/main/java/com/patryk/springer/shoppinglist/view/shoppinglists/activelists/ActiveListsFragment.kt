package com.patryk.springer.shoppinglist.view.shoppinglists.activelists

import android.os.Bundle
import android.view.ActionMode
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.patryk.springer.shoppinglist.R
import com.patryk.springer.shoppinglist.view.shoppinglists.BaseListFragment
import kotlinx.android.synthetic.main.fragment_shopping_lists.*
import javax.inject.Inject

/**
 * Created by Patryk Springer on 2019-06-13.
 */
class ActiveListsFragment : BaseListFragment<ActiveListContract.Presenter>(),
		ActiveListContract.View, ActionMode.Callback {


	@Inject
	override lateinit var mPresenter: ActiveListContract.Presenter
	override val mFragmentTitle: Int
		get() = R.string.title_active_lists

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		fab_active_shopping_list_create.setOnClickListener {
			mPresenter.onCreateNewListClicked()
		}
	}

	override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
		return when (item?.itemId) {
			R.id.menu_archive -> {
				mPresenter.onShoppingListArchived()
				mode?.finish()
				true
			}
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
		inflater?.inflate(R.menu.active_list_menu, menu)
		return true
	}

	override fun showNewListDialog() {
		AlertDialog.Builder(requireContext()).setTitle(R.string.create_new_list_title)
				.setView(LayoutInflater.from(context).inflate(R.layout.dialog_create_list, null))
				.setPositiveButton(R.string.confirm_create_new_list) { dialog, _ ->
					val etNewListName =
						(dialog as AlertDialog).findViewById<EditText>(R.id.et_create_list_name)
					val name = etNewListName?.text.toString()
					if (name.isNotBlank()) {
						mPresenter.onListSaved(name)
					}
				}.setNegativeButton(R.string.cancel_create_new_list, null).create().show()
	}
}