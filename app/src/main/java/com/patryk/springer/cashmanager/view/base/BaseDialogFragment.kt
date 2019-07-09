package com.patryk.springer.cashmanager.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment

/**
 * Created by Patryk Springer on 2019-06-16.
 */
abstract class BaseDialogFragment : DialogFragment() {

    protected abstract val layoutResId: Int

    val mBaseActivity: BaseActivity
        get() = activity as BaseActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(layoutResId, container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog.window.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

    }
}