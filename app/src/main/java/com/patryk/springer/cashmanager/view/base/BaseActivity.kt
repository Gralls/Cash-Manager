package com.patryk.springer.cashmanager.view.base

import android.os.Bundle
import com.patryk.springer.cashmanager.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Patryk Springer on 2019-06-13.
 */

abstract class BaseActivity : DaggerAppCompatActivity() {

    protected abstract val mLayoutResId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(mLayoutResId)
    }

    override fun setContentView(layoutResID: Int) {
        layoutInflater.inflate(layoutResID, fl_main_container, true)
    }

    fun setChildView(newFragment: BaseFragment<*>) {
        setView(newFragment, true)
    }

    fun setView(newFragment: BaseFragment<*>) {
        setView(newFragment, false)
    }

    private fun setView(newFragment: BaseFragment<*>, addToBackstack: Boolean) {
        supportFragmentManager.beginTransaction().apply {
            if (addToBackstack) {
                addToBackStack(null)
            }
            replace(R.id.fl_main_container, newFragment)
            commit()
        }
    }

}