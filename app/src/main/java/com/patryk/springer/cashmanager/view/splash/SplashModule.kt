package com.patryk.springer.cashmanager.view.splash

import com.patryk.springer.cashmanager.di.scopes.ActivityScope
import dagger.Binds
import dagger.Module

/**
 * Created by Patryk Springer on 2019-07-10.
 */

@Module
abstract class SplashModule {

    @ActivityScope
    @Binds
    abstract fun bindsSplashPresenter(presenter: SplashPresenter): SplashContract.Presenter

    @ActivityScope
    @Binds
    abstract fun bindsSplashView(view: SplashActivity): SplashContract.View
}