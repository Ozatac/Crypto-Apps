package com.tunahanozatac.cryptoapps.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.tunahanozatac.cryptoapps.R
import com.tunahanozatac.cryptoapps.domain.usecase.login.signup.SignUpUseCase
import com.tunahanozatac.cryptoapps.util.Resource
import com.tunahanozatac.cryptoapps.util.isValidEmailAddress
import com.tunahanozatac.cryptoapps.util.isValidPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _invalidEmailMessage = MutableLiveData<Int>()
    val invalidEmailMessage: LiveData<Int> get() = _invalidEmailMessage

    private val _invalidPasswordMessage = MutableLiveData<Int>()
    val invalidPasswordMessage: LiveData<Int> get() = _invalidPasswordMessage

    private val _passwordMessage = MutableLiveData<Int>()
    val notSamePasswordMessage: LiveData<Int> get() = _passwordMessage

    private val _signUpFlow = MutableSharedFlow<Resource<AuthResult>>()
    val signUpFlow = _signUpFlow.asSharedFlow()


    fun register(email: String, password: String, confirmPassword: String) {
        if (isEmailValid(email).not()) return
        if (isPasswordValid(password, confirmPassword)) {
            registerEmailAndPassword(email, password)
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return if (email.isValidEmailAddress().not()) {
            _invalidEmailMessage.postValue(R.string.register_email_error)
            false
        } else {
            true
        }
    }

    private fun isPasswordValid(password: String, confirmPassword: String): Boolean {
        return if (password.isValidPassword()) {
            if (password == confirmPassword) {
                true
            } else {
                _passwordMessage.postValue(R.string.register_not_match_password_error)
                false
            }
        } else {
            _invalidPasswordMessage.postValue(R.string.register_password_error)
            false
        }
    }

    private fun registerEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        signUpUseCase.invoke(email, password).collect {
            _signUpFlow.emit(it)
        }
    }
}