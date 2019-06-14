package com.patryk.springer.shoppinglist.view.main

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.patryk.springer.shoppinglist.R
import com.patryk.springer.shoppinglist.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {
    override val mLayoutResId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val newFragment = MainFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_main_container, newFragment).commit()
    }

    fun setViewPagerForTableLayout(viewPager: ViewPager?) {
        tl_main_tabs_container.setupWithViewPager(viewPager)
        setTableLayoutVisibility(viewPager != null)
    }

    private fun setTableLayoutVisibility(isVisible: Boolean) {
        tl_main_tabs_container.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    fun setToolbarTitle(titleId: Int) {
        tb_main_toolbar?.title = getString(titleId)
    }

}
