package com.patryk.springer.shoppinglist.view.listdetails

import android.content.DialogInterface
import android.os.Bundle
import android.view.ActionMode
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.patryk.springer.shoppinglist.R
import com.patryk.springer.shoppinglist.view.base.BaseFragment
import com.patryk.springer.shoppinglist.view.main.MainActivity
import kotlinx.android.synthetic.main.dialog_create_product.view.*
import kotlinx.android.synthetic.main.fragment_shopping_details.*
import javax.inject.Inject

/**
 * Created by Patryk Springer on 2019-06-15.
 */

class ListDetailsFragment : BaseFragment<ListDetailsContract.Presenter>(), ListDetailsContract.View,
		ActionMode.Callback {


	override val layoutResId: Int
		get() = R.layout.fragment_shopping_details
	@Inject
	override lateinit var mPresenter: ListDetailsContract.Presenter
	private val mAdapter: ProductsListAdapter by lazy { ProductsListAdapter(mPresenter) }
	private var mActionMode: ActionMode? = null

	companion object {
		private const val LIST_ID_KEY: String = "listId"

		fun newInstance(listId: Int): ListDetailsFragment {
			val fragment: ListDetailsFragment = ListDetailsFragment()
			val bundle: Bundle = Bundle()
			bundle.putInt(LIST_ID_KEY, listId)
			fragment.arguments = bundle
			return fragment
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		rv_shopping_details_list.adapter = mAdapter
		rv_shopping_details_list.layoutManager = LinearLayoutManager(context)

		fab_shopping_details_add_product.setOnClickListener {
			showNewProductDialog()
		}

		arguments?.let { bundle ->
			val listId: Int = bundle.get(LIST_ID_KEY) as Int
			mPresenter.refreshView(listId)
		}
	}

	private fun showNewProductDialog() {
		val view = LayoutInflater.from(context).inflate(R.layout.dialog_create_product, null)
		val positiveCallback: DialogInterface.OnClickListener =
			DialogInterface.OnClickListener { dialog, which ->
				val etNewProductName =
					(dialog as AlertDialog).findViewById<EditText>(R.id.et_create_product_name)
				val name = etNewProductName?.text.toString()
				val etNewProductQuantity =
					dialog.findViewById<EditText>(R.id.et_create_product_quantity)
				val quantity: Int = etNewProductQuantity?.text?.toString()?.toIntOrNull() ?: 0
				if (name.isNotBlank() && quantity > 0) {
					mPresenter.onNewProductAdded(name, quantity)
				}
			}
		showAlertDialog(R.string.create_new_product_title, view, positiveCallback)
	}

	override fun showAddNewProductButton(isVisible: Boolean) {
		if (isVisible) {
			fab_shopping_details_add_product.show()
		} else {
			fab_shopping_details_add_product.hide()
		}
	}

	override fun showListName(name: String, isArchived: Boolean) {
		val listName = if (isArchived) {
			"$name [${getString(R.string.archived_list)}]"
		} else name
		(mBaseActivity as? MainActivity)?.setToolbarTitle(listName)
	}

	override fun refreshList() {
		mAdapter.notifyDataSetChanged()
	}

	override fun showActionMenu() {
		mActionMode = (mBaseActivity as? MainActivity)?.showActionMenu(this)
	}

	override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
		return when (item?.itemId) {
			R.id.menu_delete -> {
				mPresenter.onProductRemoved()
				mode?.finish()
				true
			}
			R.id.menu_edit -> {
				mPresenter.onProductEditClicked()
				mode?.finish()
				true
			}
			else -> false
		}
	}

	override fun showEditProductDialog(name: String, quantity: Int) {
		val view = LayoutInflater.from(context).inflate(R.layout.dialog_create_product, null)
		view.et_create_product_name.setText(name)
		view.et_create_product_quantity.setText(quantity.toString())
		val positiveCallback: DialogInterface.OnClickListener =
			DialogInterface.OnClickListener { dialog, which ->
				val etNewProductName =
					(dialog as AlertDialog).findViewById<EditText>(R.id.et_create_product_name)
				val name = etNewProductName?.text.toString()
				val etNewProductQuantity =
					dialog.findViewById<EditText>(R.id.et_create_product_quantity)
				val quantity: Int = etNewProductQuantity?.text?.toString()?.toIntOrNull() ?: 0
				if (name.isNotBlank() && quantity > 0) {
					mPresenter.onProductEditConfirmed(name, quantity)
				}
			}
		showAlertDialog(R.string.edit_product_title, view, positiveCallback)
	}

	override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
		val inflater = mode?.menuInflater
		inflater?.inflate(R.menu.product_action_menu, menu)
		return true
	}

	override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
		return false
	}

	override fun onDestroyActionMode(mode: ActionMode?) {
		mActionMode = null
	}
}