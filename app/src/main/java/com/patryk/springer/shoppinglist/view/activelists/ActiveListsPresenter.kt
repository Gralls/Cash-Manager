package com.patryk.springer.shoppinglist.view.activelists

import javax.inject.Inject

/**
 * Created by Patryk Springer on 2019-06-14.
 */
class ActiveListsPresenter @Inject constructor(
    private val mView: ActiveListContract.View
) : ActiveListContract.Presenter {
    override fun onAttach() {

    }

    override fun onDetach() {
    }
}