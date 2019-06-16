package com.patryk.springer.shoppinglist.view.base

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.patryk.springer.shoppinglist.R
import dagger.android.support.DaggerFragment

/**
 * Created by Patryk Springer on 2019-06-13.
 */
abstract class BaseFragment<out T : BaseContract.Presenter> : DaggerFragment(),
		BaseContract.View<T> {

	val mBaseActivity: BaseActivity
		get() = activity as BaseActivity

	protected abstract val layoutResId: Int

	override fun onAttach(context: Context) {
		super.onAttach(context)
		if (context !is BaseActivity) {
			throw IllegalArgumentException(
					"BaseFragment must be attached to BaseActivity. " + context.javaClass.name + " does not extends BaseActivity")
		}
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
							  savedInstanceState: Bundle?): View? {
		return inflater.inflate(layoutResId, container, false)
	}

	override fun onStart() {
		super.onStart()
		mPresenter.onAttach()
	}

	override fun onStop() {
		mPresenter.onDetach()
		super.onStop()
	}

	fun showAlertDialog(@StringRes
						title: Int, view: View, callback: DialogInterface.OnClickListener) {
		AlertDialog.Builder(requireContext()).setTitle(title).setView(view)
				.setPositiveButton(R.string.confirm_create_new_list, callback)
				.setNegativeButton(R.string.cancel_create_new_list, null).create().show()
	}
}