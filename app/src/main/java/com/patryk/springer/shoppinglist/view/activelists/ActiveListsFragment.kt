package com.patryk.springer.shoppinglist.view.activelists

import com.patryk.springer.shoppinglist.R
import com.patryk.springer.shoppinglist.view.base.BaseViewPagerFragment
import javax.inject.Inject

/**
 * Created by Patryk Springer on 2019-06-13.
 */
class ActiveListsFragment : BaseViewPagerFragment<ActiveListContract.Presenter>(),
    ActiveListContract.View {

    @Inject
    override lateinit var mPresenter: ActiveListContract.Presenter
    override val mFragmentTitle: Int
        get() = R.string.title_active_lists
    override val layoutResId: Int
        get() = R.layout.fragment_active_shopping_lists
}