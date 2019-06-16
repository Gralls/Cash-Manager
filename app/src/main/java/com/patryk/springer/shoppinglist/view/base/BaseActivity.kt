package com.patryk.springer.shoppinglist.view.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.patryk.springer.shoppinglist.R
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

    fun hideKeyboard() {
        hideKeyboard(currentFocus)
    }

    fun hideKeyboard(view: View?) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        view?.let { focusedView ->
            val inputMethodManager: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}