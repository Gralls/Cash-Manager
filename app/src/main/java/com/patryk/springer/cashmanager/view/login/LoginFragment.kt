package com.patryk.springer.cashmanager.view.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding2.widget.RxTextView
import com.patryk.springer.cashmanager.R
import com.patryk.springer.cashmanager.view.base.BaseFragment
import com.patryk.springer.cashmanager.view.main.MainActivity
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

/**
 * Created by Patryk Springer on 2019-07-10.
 */

class LoginFragment : BaseFragment<LoginContract.Presenter>(), LoginContract.View {
    @Inject
    override lateinit var mPresenter: LoginContract.Presenter

    private val mDisposable: CompositeDisposable = CompositeDisposable()
    override val layoutResId: Int
        get() = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailSubscribtion: Observable<String> =
            RxTextView.textChanges(et_login_email).map { it.toString() }
        val passwordSubscribtion: Observable<String> =
            RxTextView.textChanges(et_login_password).map { it.toString() }

        mDisposable += Observables.combineLatest(
            emailSubscribtion,
            passwordSubscribtion
        ) { email: String, password: String -> email.isNotBlank() && password.isNotBlank() }
            .subscribe { allInputsFilled ->
                btn_login_sign_in.isEnabled = allInputsFilled
            }

        btn_login_sign_in.setOnClickListener {
            mPresenter.onSubmitClicked(
                et_login_email.text.toString(),
                et_login_password.text.toString()
            )
        }
    }

    override fun openLoggedInView() {
        startActivity(Intent(context, MainActivity::class.java))
        mBaseActivity.finish()
    }

    override fun showEmailError(errorId: Int?) {
        til_login_email.error = getErrorMessage(errorId)
    }

    override fun showPasswordError(errorId: Int?) {
        til_login_password.error = getErrorMessage(errorId)
    }

    private fun getErrorMessage(errorId: Int?): String? =
        if (errorId == null) null else getString(errorId)

    override fun showLoginError() {
        Toast.makeText(context, R.string.error_auth_wrong_credentials, Toast.LENGTH_LONG).show()
    }
}