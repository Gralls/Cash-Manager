package com.patryk.springer.cashmanager.di.modules

import com.patryk.springer.cashmanager.di.scopes.ActivityScope
import com.patryk.springer.cashmanager.view.main.MainActivity
import com.patryk.springer.cashmanager.view.main.MainModule
import com.patryk.springer.cashmanager.view.splash.SplashActivity
import com.patryk.springer.cashmanager.view.splash.SplashModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Patryk Springer on 2019-06-14.
 */
@Module
abstract class ActivitiesModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [(MainModule::class)])
    internal abstract fun mainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(SplashModule::class)])
    internal abstract fun splashActivity(): SplashActivity
}