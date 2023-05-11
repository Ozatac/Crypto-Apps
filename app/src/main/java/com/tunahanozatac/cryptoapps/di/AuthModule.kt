package com.tunahanozatac.cryptoapps.di

import com.tunahanozatac.cryptoapps.domain.repository.Authenticator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tunahanozatac.cryptoapps.data.repository.FirebaseAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideFirebaseAuthenticator(
        firebaseAuth: FirebaseAuth, firebaseFirestore: FirebaseFirestore
    ): Authenticator = FirebaseAuthenticator(firebaseAuth, firebaseFirestore)
}