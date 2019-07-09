package com.patryk.springer.cashmanager.view.base

import android.os.Bundle
import android.view.View
import com.patryk.springer.cashmanager.view.main.MainActivity
import kotlinx.android.synthetic.main.base_view_pager.*

/**
 * Created by Patryk Springer on 2019-06-13.
 */
abstract class BaseFragmentWithViewPager<out T : BaseContract.Presenter> : BaseFragment<T>() {

    abstract val mListOfFragments: List<BaseViewPagerFragment<*>>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (vp_base_pager == null) {
            throw IllegalArgumentException("Layout must include base_view_pager")
        }
        vp_base_pager.adapter = ViewPagerAdapter(childFragmentManager, mListOfFragments, context)
        (mBaseActivity as MainActivity).setViewPagerForTableLayout(vp_base_pager)
    }

    override fun onResume() {
        super.onResume()
        (mBaseActivity as MainActivity).setViewPagerForTableLayout(vp_base_pager)
    }

    override fun onStop() {
        super.onStop()
        (mBaseActivity as MainActivity).setViewPagerForTableLayout(null)
    }
}