package com.patryk.springer.shoppinglist.di.modules

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

/**
 * Created by Patryk Springer on 2019-06-14.
 */
@Module
abstract class ApplicationModule {
    @Binds
    internal abstract fun bindContext(application: Application): Context

}