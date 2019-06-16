package com.patryk.springer.shoppinglist.view.shoppinglists.activelists

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding2.widget.RxTextView
import com.patryk.springer.shoppinglist.R
import com.patryk.springer.shoppinglist.view.base.BaseDialogFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.dialog_create_list.*

/**
 * Created by Patryk Springer on 2019-06-16.
 */

class NewListDialogFragment : BaseDialogFragment() {

    override val layoutResId: Int
        get() = R.layout.dialog_create_list

    private val mDisposable: CompositeDisposable = CompositeDisposable()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDisposable += RxTextView.textChanges(et_create_list_name).map { it.isNotBlank() }
            .subscribe {
                btn_create_list_submit.isEnabled = it
            }
        btn_create_list_submit.setOnClickListener {
            val listName: String = et_create_list_name.text.toString()
            val intent = Intent()
            intent.putExtra(ActiveListsFragment.NEW_LIST_NAME_KEY, listName)
            targetFragment?.onActivityResult(
                ActiveListsFragment.NEW_LIST_INTENT_CODE,
                Activity.RESULT_OK,
                intent
            )
            dismiss()

        }
    }

    override fun onPause() {
        super.onPause()
        mDisposable.clear()
    }
}
