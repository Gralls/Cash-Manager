package com.patryk.springer.shoppinglist.view.archievedlists

import com.patryk.springer.shoppinglist.R
import com.patryk.springer.shoppinglist.view.base.BaseViewPagerFragment
import javax.inject.Inject

/**
 * Created by Patryk Springer on 2019-06-13.
 */
class ArchivedListsFragment : BaseViewPagerFragment<ArchivedListsContract.Presenter>(),
    ArchivedListsContract.View {

    @Inject
    override lateinit var mPresenter: ArchivedListsContract.Presenter

    override val mFragmentTitle: Int
        get() = R.string.title_archived_lists
    override val layoutResId: Int
        get() = R.layout.fragment_archived_shopping_list
}