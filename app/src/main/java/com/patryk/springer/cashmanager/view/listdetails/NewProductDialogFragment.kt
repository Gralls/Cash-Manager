package com.patryk.springer.cashmanager.view.listdetails

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding2.widget.RxTextView
import com.patryk.springer.cashmanager.R
import com.patryk.springer.cashmanager.view.base.BaseDialogFragment
import com.patryk.springer.cashmanager.view.listdetails.ListDetailsFragment.EDIT_PRODUCT_INTENT_CODE
import com.patryk.springer.cashmanager.view.listdetails.ListDetailsFragment.NEW_PRODUCT_INTENT_CODE
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.dialog_create_product.*

/**
 * Created by Patryk Springer on 2019-06-16.
 */
class NewProductDialogFragment : BaseDialogFragment() {
    override val layoutResId: Int
        get() = R.layout.dialog_create_product
    private val mDisposable: CompositeDisposable = CompositeDisposable()

    companion object {
        private const val PRODUCT_NAME_KEY = "productName"
        private const val PRODUCT_QUANTITY_KEY = "productQuantity"
        fun newInstance(name: String, quantity: Int): NewProductDialogFragment {
            val fragment = NewProductDialogFragment()
            val bundle = Bundle()
            bundle.putString(PRODUCT_NAME_KEY, name)
            bundle.putInt(PRODUCT_QUANTITY_KEY, quantity)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.run {
            val prodName = getString(PRODUCT_NAME_KEY) ?: ""
            val quantity = getInt(PRODUCT_QUANTITY_KEY).toString()
            et_create_product_name.setText(prodName)
            et_create_product_quantity.setText(quantity)
            tv_create_new_product_title.setText(R.string.edit_product_title)
        }
        val prodNameSubscribtion: Observable<String> =
            RxTextView.textChanges(et_create_product_name).map { it.toString() }
        val prodQuantitySubscribtion: Observable<Int> =
            RxTextView.textChanges(et_create_product_quantity)
                .map { it.toString().toIntOrNull() ?: -1 }

        mDisposable += Observables.combineLatest(
            prodNameSubscribtion,
            prodQuantitySubscribtion
        ) { name: String, quantity: Int -> name.isNotBlank() && quantity > 0 }
            .subscribe { allInputsFilled ->
                btn_create_product_submit.isEnabled = allInputsFilled
            }
        btn_create_product_submit.setOnClickListener {
            onSubmitClicked()
        }
    }


    override fun onPause() {
        super.onPause()
        mDisposable.clear()
    }

    private fun onSubmitClicked() {
        val name: String = et_create_product_name.text?.toString() ?: ""
        val quantity: Int = et_create_product_quantity.text?.toString()?.toIntOrNull() ?: -1
        val isNewProduct = arguments == null
        val intent = Intent().apply {
            putExtra(ListDetailsFragment.PRODUCT_NAME_KEY, name)
            putExtra(ListDetailsFragment.PRODUCT_QUANTITY_KEY, quantity)
        }

        val requestCode =
            if (isNewProduct) NEW_PRODUCT_INTENT_CODE else EDIT_PRODUCT_INTENT_CODE

        targetFragment?.onActivityResult(
            requestCode,
            Activity.RESULT_OK,
            intent
        )
        dismiss()
    }

}