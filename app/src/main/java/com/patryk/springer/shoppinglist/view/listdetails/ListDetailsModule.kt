package com.patryk.springer.shoppinglist.view.listdetails

import com.patryk.springer.shoppinglist.di.scopes.FragmentScope
import dagger.Binds
import dagger.Module

/**
 * Created by Patryk Springer on 2019-06-15.
 */

@Module
abstract class ListDetailsModule {

	@FragmentScope
	@Binds
	abstract fun bindListDetailsPresenter(
			presenter: ListDetailsPresenter): ListDetailsContract.Presenter

	@FragmentScope
	@Binds
	abstract fun bindsListDetailsView(view: ListDetailsFragment): ListDetailsContract.View
}