package com.patryk.springer.cashmanager.view.main

import android.os.Bundle
import android.view.ActionMode
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.patryk.springer.cashmanager.R
import com.patryk.springer.cashmanager.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override val mLayoutResId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState ?: setView(MainFragment())
    }

    fun setViewPagerForTableLayout(viewPager: ViewPager?) {
        tl_main_tabs_container.setupWithViewPager(viewPager)
        setTableLayoutVisibility(viewPager != null)
    }

    private fun setTableLayoutVisibility(isVisible: Boolean) {
        tl_main_tabs_container.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    fun setToolbarTitle(title: String) {
        tb_main_toolbar?.title = title
    }

    fun setToolbarTitle(titleId: Int) {
        tb_main_toolbar?.title = getString(titleId)
    }

    fun showActionMenu(callback: ActionMode.Callback): ActionMode =
        tb_main_toolbar.startActionMode(callback)
}
