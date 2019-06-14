package com.patryk.springer.shoppinglist.view.base

/**
 * Created by Patryk Springer on 2019-06-13.
 */
abstract class BaseViewPagerFragment<out T : BaseContract.Presenter> : BaseFragment<T>() {

    abstract val mFragmentTitle: Int
}
