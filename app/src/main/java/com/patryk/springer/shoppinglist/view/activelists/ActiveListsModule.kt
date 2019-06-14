package com.patryk.springer.shoppinglist.view.activelists

import com.patryk.springer.shoppinglist.di.scopes.FragmentScope
import dagger.Binds
import dagger.Module

/**
 * Created by Patryk Springer on 2019-06-14.
 */

@Module
abstract class ActiveListsModule {

    @FragmentScope
    @Binds
    abstract fun bindActiveListsPresenter(presenter: ActiveListsPresenter): ActiveListContract.Presenter

    @FragmentScope
    @Binds
    abstract fun bindsActiveListsView(view: ActiveListsFragment): ActiveListContract.View
}