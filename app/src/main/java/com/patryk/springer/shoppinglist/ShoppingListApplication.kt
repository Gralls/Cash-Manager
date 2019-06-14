package com.patryk.springer.shoppinglist

import com.patryk.springer.shoppinglist.di.components.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by Patryk Springer on 2019-06-13.
 */
class ShoppingListApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().application(this).build()
    }
}