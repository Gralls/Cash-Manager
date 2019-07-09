package com.patryk.springer.cashmanager.di.components

import android.app.Application
import com.patryk.springer.cashmanager.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

/**
 * Created by Patryk Springer on 2019-06-13.
 */
@Singleton
@Component(
    modules = [ApplicationModule::class, AndroidSupportInjectionModule::class, FragmentsModule::class, ActivitiesModule::class, DatabaseModule::class, ShoppingListsRepoModule::class]
)
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}