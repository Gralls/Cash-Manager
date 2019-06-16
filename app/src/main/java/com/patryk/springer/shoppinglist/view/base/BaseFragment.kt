package com.patryk.springer.shoppinglist.view.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                "BaseFragment must be attached to BaseActivity. " + context.javaClass.name + " does not extends BaseActivity"
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
}