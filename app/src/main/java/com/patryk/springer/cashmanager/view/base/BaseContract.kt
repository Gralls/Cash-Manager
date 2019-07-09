package com.patryk.springer.cashmanager.view.base

/**
 * Created by Patryk Springer on 2019-06-13.
 */
interface BaseContract {

    interface View<out T : Presenter> {

        val mPresenter: T

    }

    interface Presenter {

        fun onAttach()

        fun onDetach()

    }
}