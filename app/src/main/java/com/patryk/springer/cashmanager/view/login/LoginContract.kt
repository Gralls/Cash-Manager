package com.patryk.springer.cashmanager.view.login

import com.patryk.springer.cashmanager.view.base.BaseContract

/**
 * Created by Patryk Springer on 2019-07-10.
 */
interface LoginContract {
    interface View : BaseContract.View<Presenter> {
        fun showEmailError(errorId: Int?)
        fun showPasswordError(errorId: Int?)
        fun showLoginError()
        fun openLoggedInView()
    }

    interface Presenter : BaseContract.Presenter {
        fun onSubmitClicked(email: String, password: String)
    }
}