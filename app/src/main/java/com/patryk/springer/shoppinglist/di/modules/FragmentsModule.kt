package com.patryk.springer.shoppinglist.di.modules

import com.patryk.springer.shoppinglist.di.scopes.FragmentScope
import com.patryk.springer.shoppinglist.view.main.MainFragment
import com.patryk.springer.shoppinglist.view.main.MainModule
import com.patryk.springer.shoppinglist.view.shoppinglists.activelists.ActiveListsFragment
import com.patryk.springer.shoppinglist.view.shoppinglists.activelists.ActiveListsModule
import com.patryk.springer.shoppinglist.view.shoppinglists.archievedlists.ArchivedListsFragment
import com.patryk.springer.shoppinglist.view.shoppinglists.archievedlists.ArchivedListsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Patryk Springer on 2019-06-14.
 */

@Module
abstract class FragmentsModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun mainFragment(): MainFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [ActiveListsModule::class])
    internal abstract fun activeListsFragment(): ActiveListsFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [ArchivedListsModule::class])
    internal abstract fun archivedListsFragment(): ArchivedListsFragment
}