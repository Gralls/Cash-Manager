package com.patryk.springer.cashmanager.di.modules

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.Module
import dagger.Provides


/**
 * Created by Patryk Springer on 2019-07-10.
 */
@Module
class FirebaseModule {

    @Provides
    fun provideUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}