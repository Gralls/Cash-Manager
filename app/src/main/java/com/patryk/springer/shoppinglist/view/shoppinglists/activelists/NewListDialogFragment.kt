package com.patryk.springer.shoppinglist.view.shoppinglists.activelists

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding2.widget.RxTextView
import com.patryk.springer.shoppinglist.R
import com.patryk.springer.shoppinglist.view.base.BaseDialogFragment
import com.patryk.springer.shoppinglist.view.shoppinglists.activelists.ActiveListsFragment.Companion.EDIT_LIST_INTENT_CODE
import com.patryk.springer.shoppinglist.view.shoppinglists.activelists.ActiveListsFragment.Companion.NEW_LIST_INTENT_CODE
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.dialog_create_list.*

/**
 * Created by Patryk Springer on 2019-06-16.
 */

class NewListDialogFragment : BaseDialogFragment() {

    override val layoutResId: Int
        get() = R.layout.dialog_create_list

    companion object {
        private const val LIST_NAME_KEY = "listName"
        fun newInstance(listName: String): NewListDialogFragment {
            val fragment = NewListDialogFragment()
            val bundle = Bundle()
            bundle.putString(LIST_NAME_KEY, listName)
            fragment.arguments = bundle
            return fragment
        }
    }

    private val mDisposable: CompositeDisposable = CompositeDisposable()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareEditViewIfNeeded()

        mDisposable += RxTextView.textChanges(et_create_list_name).map { it.isNotBlank() }
            .subscribe {
                btn_create_list_submit.isEnabled = it
            }

        btn_create_list_submit.setOnClickListener {
            handleSubmitButtonClick()
        }
    }

    private fun prepareEditViewIfNeeded() {
        arguments?.run {
            val listName = getString(LIST_NAME_KEY, "")
            et_create_list_name.setText(listName)
            tv_create_list_title.setText(R.string.edit_list_title)
        }
    }

    private fun handleSubmitButtonClick() {
        val listName: String = et_create_list_name.text.toString()
        val intent = Intent()
        intent.putExtra(ActiveListsFragment.NEW_LIST_NAME_KEY, listName)
        val requestCode =
            if (arguments == null) NEW_LIST_INTENT_CODE else EDIT_LIST_INTENT_CODE
        targetFragment?.onActivityResult(
            requestCode,
            Activity.RESULT_OK,
            intent
        )
        dismiss()
    }

    override fun onPause() {
        super.onPause()
        mDisposable.clear()
    }
}
