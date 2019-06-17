package com.patryk.springer.shoppinglist.view.shoppinglists.activelists

import android.app.Activity
import android.content.Intent
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
class ActiveListsFragment : BaseListFragment<ActiveListContract.Presenter>(),
    ActiveListContract.View {


    @Inject
    override lateinit var mPresenter: ActiveListContract.Presenter
    override val mFragmentTitle: Int
        get() = R.string.title_active_lists

    companion object {
        const val NEW_LIST_NAME_KEY = "listName"
        const val NEW_LIST_INTENT_CODE = 5729
        const val EDIT_LIST_INTENT_CODE = 8561
    }

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
            R.id.menu_edit -> {
                mPresenter.onShoppingListEdit()
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
        val dialog = NewListDialogFragment()
        dialog.setTargetFragment(this, NEW_LIST_INTENT_CODE)
        dialog.show(fragmentManager, null)

    }

    override fun showEditListDialog(listName: String) {
        val dialog = NewListDialogFragment.newInstance(listName)
        dialog.setTargetFragment(this, EDIT_LIST_INTENT_CODE)
        dialog.show(fragmentManager, null)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            data?.run {
                val listName = getStringExtra(NEW_LIST_NAME_KEY)
                if (requestCode == NEW_LIST_INTENT_CODE) {
                    mPresenter.onListSaved(listName)
                } else if (requestCode == EDIT_LIST_INTENT_CODE) {
                    mPresenter.onShoppingListEditSaved(listName)
                }

            }
        }

    }
}