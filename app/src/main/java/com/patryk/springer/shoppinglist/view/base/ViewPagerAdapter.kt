package com.patryk.springer.shoppinglist.view.base

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by Patryk Springer on 2019-06-13.
 */
class ViewPagerAdapter(
    fragmentManager: FragmentManager?,
    private val mPages: List<BaseViewPagerFragment<*>>,
    private val mContext: Context?
) : FragmentPagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment = mPages[position]

    override fun getCount(): Int = mPages.size

    override fun getPageTitle(position: Int): CharSequence? =
        mContext?.getString(mPages[position].mFragmentTitle)
}
