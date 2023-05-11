package com.tunahanozatac.cryptoapps.ui.splash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tunahanozatac.cryptoapps.core.BaseFragment
import com.tunahanozatac.cryptoapps.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    override val viewModel: SplashViewModel by viewModels()

    override fun viewCreated() {
        lifecycleScope.launch {
            delay(4000)
            val action = viewModel.getNavigateAction()
            findNavController().navigate(action)
        }
    }

    override fun layoutResource(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentSplashBinding.inflate(inflater, container, false)
}