package com.tunahanozatac.cryptoapps.ui.register

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.tunahanozatac.cryptoapps.R
import com.tunahanozatac.cryptoapps.core.BaseFragment
import com.tunahanozatac.cryptoapps.util.Resource
import com.tunahanozatac.cryptoapps.data.model.AuthenticationModel
import com.tunahanozatac.cryptoapps.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {
    override val viewModel: RegisterViewModel by viewModels()

    override fun layoutResource(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentRegisterBinding.inflate(inflater, container, false)

    override fun viewCreated() {
        clicks()
        observer()
        listener()
    }

    private fun clicks() {
        binding?.apply {
            signupButton.setOnClickListener {
                viewModel.register(getInfo().email, getInfo().password, getInfo().confirmPassword)
            }

            loginRedirectText.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }
    }

    private fun observer() {
        binding?.apply {
            with(viewModel) {
                viewModel.invalidEmailMessage.observe(viewLifecycleOwner) { invalidEmailMessage ->
                    invalidEmailMessage?.let {
                        signupEmail.error = getString(it)
                        configureVisibility(errorText, true)
                        errorText.text = getText(it)
                    }
                }

                viewModel.invalidPasswordMessage.observe(
                    viewLifecycleOwner
                ) { invalidPasswordMessage ->
                    invalidPasswordMessage?.let {
                        signupPassword.error = getString(it)
                        configureVisibility(errorText, true)
                        errorText.text = getText(it)
                    }
                }

                viewModel.notSamePasswordMessage.observe(
                    viewLifecycleOwner
                ) { notSamePasswordMessage ->
                    notSamePasswordMessage?.let {
                        signupPasswordConfirm.error = getString(it)
                        configureVisibility(errorText, true)
                        errorText.text = getText(it)
                    }
                }

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    signUpFlow.collect { result ->
                        when (result) {
                            is Resource.Loading -> {
                                configureVisibility(binding?.loadingInc?.root, true)
                                configureVisibility(binding?.errorInc?.root, false)
                            }

                            is Resource.Success -> {
                                configureVisibility(binding?.errorInc?.root, false)
                                configureVisibility(binding?.loadingInc?.root, false)
                                findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
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

    private fun listener() {
        binding?.apply {
            signupEmail.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus: Boolean ->
                if (hasFocus) {
                    signupEmail.error = null
                }
            }

            signupPassword.onFocusChangeListener =
                View.OnFocusChangeListener { _, hasFocus: Boolean ->
                    if (hasFocus) {
                        signupPassword.error = null
                    }
                }

            signupPasswordConfirm.onFocusChangeListener =
                View.OnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        signupPassword.error = null
                    }
                }
        }
    }

    private fun getInfo(): AuthenticationModel {
        val email = binding?.signupEmail?.text
        val password = binding?.signupPassword?.text
        val confirmPassword = binding?.signupPasswordConfirm?.text
        return AuthenticationModel(
            email.toString(), password.toString(), confirmPassword.toString()
        )
    }
}