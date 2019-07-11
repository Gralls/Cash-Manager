package com.patryk.springer.cashmanager.view.login

import android.util.Patterns
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.patryk.springer.cashmanager.R
import javax.inject.Inject

/**
 * Created by Patryk Springer on 2019-07-10.
 */
class LoginPresenter @Inject constructor(
    private val mView: LoginContract.View,
    private val mFirebaseAuth: FirebaseAuth
) : LoginContract.Presenter {
    override fun onAttach() = Unit

    override fun onDetach() = Unit

    override fun onSubmitClicked(email: String, password: String) {
        val isEmailValid = isEmailValid(email)
        val isPasswordValid = isPasswordValid(password)
        if (!(isEmailValid && isPasswordValid)) return
        mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                mView.openLoggedInView()
            } else {
                handleLoginException(task.exception)
            }
        }
    }


    private fun isEmailValid(email: String): Boolean {
        var isValid = true
        when {
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                isValid = false
                mView.showEmailError(R.string.error_invalid_email)
            }
            else -> mView.showEmailError(null)
        }
        return isValid
    }

    private fun isPasswordValid(password: String): Boolean {
        var isValid = true
        when {
            password.isBlank() -> {
                isValid = false
                mView.showPasswordError(R.string.error_empty_field)
            }
            else -> mView.showPasswordError(null)
        }
        return isValid
    }

    private fun handleLoginException(exception: Exception?) {
        when (exception) {
            is FirebaseAuthException -> mView.showLoginError()
        }

    }
}