package com.patryk.springer.cashmanager.view.login

import com.patryk.springer.cashmanager.di.scopes.FragmentScope
import dagger.Binds
import dagger.Module

/**
 * Created by Patryk Springer on 2019-07-10.
 */
@Module
abstract class LoginModule {

    @FragmentScope
    @Binds
    abstract fun bindsSplashPresenter(presenter: LoginPresenter): LoginContract.Presenter

    @FragmentScope
    @Binds
    abstract fun bindsSplashView(view: LoginFragment): LoginContract.View
}