package com.patryk.springer.cashmanager.di.modules

import com.patryk.springer.cashmanager.di.scopes.FragmentScope
import com.patryk.springer.cashmanager.view.listdetails.ListDetailsFragment
import com.patryk.springer.cashmanager.view.listdetails.ListDetailsModule
import com.patryk.springer.cashmanager.view.main.MainFragment
import com.patryk.springer.cashmanager.view.main.MainModule
import com.patryk.springer.cashmanager.view.shoppinglists.activelists.ActiveListsFragment
import com.patryk.springer.cashmanager.view.shoppinglists.activelists.ActiveListsModule
import com.patryk.springer.cashmanager.view.shoppinglists.archievedlists.ArchivedListsFragment
import com.patryk.springer.cashmanager.view.shoppinglists.archievedlists.ArchivedListsModule
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

    @FragmentScope
    @ContributesAndroidInjector(modules = [ListDetailsModule::class])
    internal abstract fun listDetailsFragment(): ListDetailsFragment
}