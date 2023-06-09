package com.tunahanozatac.cryptoapps.ui.splash

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.tunahanozatac.cryptoapps.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val firebaseAuth: FirebaseAuth) : ViewModel() {

    fun getNavigateAction(): Int {
        val currentUser = firebaseAuth.currentUser
        return if (currentUser != null) {
            R.id.action_splashFragment_to_homeFragment
        } else {
            R.id.action_splashFragment_to_loginFragment
        }
    }
}