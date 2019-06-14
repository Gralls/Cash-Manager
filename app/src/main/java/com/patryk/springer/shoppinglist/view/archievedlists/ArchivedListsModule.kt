package com.patryk.springer.shoppinglist.view.archievedlists

import com.patryk.springer.shoppinglist.di.scopes.FragmentScope
import dagger.Binds
import dagger.Module

/**
 * Created by Patryk Springer on 2019-06-14.
 */

@Module
abstract class ArchivedListsModule {

    @FragmentScope
    @Binds
    abstract fun bindArchivedListsPresenter(
        presenter: ArchivedListsPresenter
    ): ArchivedListsContract.Presenter

    @FragmentScope
    @Binds
    abstract fun bindsArchivedListsView(view: ArchivedListsFragment): ArchivedListsContract.View
}