package com.patryk.springer.shoppinglist.di.components

import android.app.Application
import com.patryk.springer.shoppinglist.di.modules.ActivitiesModule
import com.patryk.springer.shoppinglist.di.modules.ApplicationModule
import com.patryk.springer.shoppinglist.di.modules.DatabaseModule
import com.patryk.springer.shoppinglist.di.modules.FragmentsModule
import com.patryk.springer.shoppinglist.di.modules.ShoppingListsRepoModule
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
		modules = [ApplicationModule::class, AndroidSupportInjectionModule::class, FragmentsModule::class, ActivitiesModule::class, DatabaseModule::class, ShoppingListsRepoModule::class])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

	override fun inject(instance: DaggerApplication)

	@Component.Builder
	interface Builder {

		@BindsInstance
		fun application(application: Application): Builder

		fun build(): ApplicationComponent
	}
}