package com.patryk.springer.shoppinglist.di.modules

import com.patryk.springer.shoppinglist.di.scopes.ActivityScope
import com.patryk.springer.shoppinglist.view.main.MainActivity
import com.patryk.springer.shoppinglist.view.main.MainModule
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
}