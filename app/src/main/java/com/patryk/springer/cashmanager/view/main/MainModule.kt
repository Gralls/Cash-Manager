package com.patryk.springer.cashmanager.view.main

import com.patryk.springer.cashmanager.di.scopes.ActivityScope
import com.patryk.springer.cashmanager.di.scopes.FragmentScope
import dagger.Binds
import dagger.Module

/**
 * Created by Patryk Springer on 2019-06-14.
 */
@Module
abstract class MainModule {

    @FragmentScope
    @Binds
    abstract fun bindsMainPresenter(presenter: MainPresenter): MainContract.Presenter

    @FragmentScope
    @Binds
    abstract fun bindsMainView(view: MainFragment): MainContract.View

    @ActivityScope
    @Binds
    abstract fun bindsMainAct(activity: MainActivity): MainActivity
}