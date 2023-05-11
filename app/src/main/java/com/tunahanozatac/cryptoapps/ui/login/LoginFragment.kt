package com.tunahanozatac.cryptoapps.ui.login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tunahanozatac.cryptoapps.R
import com.tunahanozatac.cryptoapps.core.BaseFragment
import com.tunahanozatac.cryptoapps.util.Resource
import com.tunahanozatac.cryptoapps.data.model.AuthenticationModel
import com.tunahanozatac.cryptoapps.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override val viewModel: LoginViewModel by viewModels()

    override fun layoutResource(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun viewCreated() {
        clicks()
        observer()
        initListener()
    }

    private fun clicks() {
        binding?.apply {
            signupRedirectText.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }

            loginButton.setOnClickListener {
                val authModel = getEmailAndPassword()
                viewModel.sigIn(authModel.email, authModel.password)
            }
        }
    }

    private fun observer() {
        binding?.apply {
            with(viewModel) {
                viewModel.invalidEmailMessage.observe(viewLifecycleOwner) { message ->
                    message?.let {
                        loginUsername.error = getString(it)
                    }
                }

                viewModel.invalidPasswordMessage.observe(viewLifecycleOwner) { message ->
                    message?.let {
                        loginPassword.error = getString(it)
                    }
                }

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    signInFlow.collect { result ->
                        when (result) {
                            is Resource.Loading -> {
                                configureVisibility(binding?.loadingInc?.root, true)
                                configureVisibility(binding?.errorInc?.root, false)
                            }

                            is Resource.Success -> {
                                configureVisibility(binding?.errorInc?.root, false)
                                configureVisibility(binding?.loadingInc?.root, false)
                                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                            }

                            is Resource.Error -> {
                                configureVisibility(binding?.errorText, true)
                                configureVisibility(binding?.loadingInc?.root, false)
                                binding?.errorText?.text = result.throwable.message.toString()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun initListener() {
        binding?.apply {
            loginUsername.onFocusChangeListener =
                View.OnFocusChangeListener { _, hasFocus: Boolean ->
                    if (hasFocus) {
                        loginUsername.error = null
                    }
                }

            loginPassword.onFocusChangeListener =
                View.OnFocusChangeListener { _, hasFocus: Boolean ->
                    if (hasFocus) {
                        loginPassword.error = null
                    }
                }
        }
    }

    private fun getEmailAndPassword(): AuthenticationModel {
        val email = binding?.loginUsername?.text
        val password = binding?.loginPassword?.text
        return AuthenticationModel(email.toString(), password.toString())
    }
}