package com.patryk.springer.cashmanager.view.main

import android.os.Bundle
import android.view.View
import com.patryk.springer.cashmanager.R
import com.patryk.springer.cashmanager.view.base.BaseFragmentWithViewPager
import com.patryk.springer.cashmanager.view.base.BaseViewPagerFragment
import com.patryk.springer.cashmanager.view.shoppinglists.activelists.ActiveListsFragment
import com.patryk.springer.cashmanager.view.shoppinglists.archievedlists.ArchivedListsFragment
import javax.inject.Inject

/**
 * Created by Patryk Springer on 2019-06-13.
 */
class MainFragment : BaseFragmentWithViewPager<MainContract.Presenter>(), MainContract.View {

    @Inject
    override lateinit var mPresenter: MainContract.Presenter

    override val mListOfFragments: List<BaseViewPagerFragment<*>>
        get() = listOf(ActiveListsFragment(), ArchivedListsFragment())
    override val layoutResId: Int
        get() = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (mBaseActivity as? MainActivity)?.setToolbarTitle(R.string.title_shopping_lists)
    }
}