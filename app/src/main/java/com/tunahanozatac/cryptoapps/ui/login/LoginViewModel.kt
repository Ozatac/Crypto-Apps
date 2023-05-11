package com.tunahanozatac.cryptoapps.ui.login

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.tunahanozatac.cryptoapps.R
import com.tunahanozatac.cryptoapps.util.Resource
import com.tunahanozatac.cryptoapps.domain.usecase.login.signin.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _invalidEmailMessage = MutableLiveData<Int>()
    val invalidEmailMessage: LiveData<Int> get() = _invalidEmailMessage

    private val _invalidPasswordMessage = MutableLiveData<Int>()
    val invalidPasswordMessage: LiveData<Int> get() = _invalidPasswordMessage

    private val _signInFlow = MutableSharedFlow<Resource<AuthResult>>()
    val signInFlow = _signInFlow.asSharedFlow()

    fun sigIn(email: String, password: String) {
        val isFieldsFull = isEmailValid(email) && isPasswordValid(password)
        if (isFieldsFull) {
            sigInEmailAndPassword(email, password)
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return if (TextUtils.isEmpty(email)) {
            _invalidEmailMessage.postValue(R.string.login_enter_email_error)
            false
        } else true
    }


    private fun isPasswordValid(password: String): Boolean {
        return if (TextUtils.isEmpty(password)) {
            _invalidPasswordMessage.postValue(R.string.login_enter_password_error)
            false
        } else true
    }

    fun sigInEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        signInUseCase.invoke(email, password).collect {
            _signInFlow.emit(it)
        }
    }
}