package com.patryk.springer.cashmanager.view.splash

import android.content.Intent
import android.os.Bundle
import com.patryk.springer.cashmanager.R
import com.patryk.springer.cashmanager.view.base.BaseActivity
import com.patryk.springer.cashmanager.view.login.LoginFragment
import com.patryk.springer.cashmanager.view.main.MainActivity
import javax.inject.Inject

/**
 * Created by Patryk Springer on 2019-07-10.
 */
class SplashActivity : BaseActivity(), SplashContract.View {
    @Inject
    override lateinit var mPresenter: SplashContract.Presenter

    override val mLayoutResId: Int
        get() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        mPresenter.onAttach()
    }

    override fun openLoginView() {
        setView(LoginFragment())
    }

    override fun openMainView() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}