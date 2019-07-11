package com.patryk.springer.cashmanager.view.splash

import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

/**
 * Created by Patryk Springer on 2019-07-10.
 */
class SplashPresenter @Inject constructor(
    private val mView: SplashContract.View,
    private val mFirebaseUser: FirebaseUser?
) : SplashContract.Presenter {
    override fun onAttach() {
        checkIsUserLoggedIn()
    }

    private fun checkIsUserLoggedIn() {
        if (mFirebaseUser != null) {
            mView.openMainView()
        } else {
            mView.openLoginView()
        }
    }

    override fun onDetach() {

    }

}