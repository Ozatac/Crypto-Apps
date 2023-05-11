package com.tunahanozatac.cryptoapps.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.tunahanozatac.cryptoapps.R
import com.tunahanozatac.cryptoapps.core.BaseFragment
import com.tunahanozatac.cryptoapps.databinding.FragmentCryptoDetailsBinding
import com.tunahanozatac.cryptoapps.util.DetailUIEffect
import com.tunahanozatac.cryptoapps.util.Resource
import com.tunahanozatac.cryptoapps.util.gone
import com.tunahanozatac.cryptoapps.util.showSnackBar
import com.tunahanozatac.cryptoapps.util.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlin.time.Duration.Companion.milliseconds

@AndroidEntryPoint
class CryptoDetailsFragment : BaseFragment<FragmentCryptoDetailsBinding, CryptoDetailsViewModel>() {

    private val coinIdArgs: CryptoDetailsFragmentArgs by navArgs()
    override val viewModel: CryptoDetailsViewModel by viewModels()
    private val secondList = listOf("10 sec", "15 sec", "30 sec", "60 sec", "90 sec")
    private val milliSecondList = listOf(10000, 15000, 30000, 60000, 90000)

    override fun layoutResource(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentCryptoDetailsBinding.inflate(inflater, container, false)

    override fun viewCreated() {
        viewModel.coinById(coinIdArgs.id)
        initUI()
        collectData()
    }

    private fun initUI() {
        val secondsAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown_menu, secondList)
        binding?.apply {
            btnFavourite.setOnClickListener {
                viewModel.addToFavourites()
            }
            actSecond.setAdapter(secondsAdapter)
            actSecond.setOnItemClickListener { _, _, position, _ ->
                viewModel.currentPriceById(milliSecondList[position].milliseconds, coinIdArgs.id)
            }
        }
    }

    private fun collectData() {
        binding?.apply {
            with(viewModel) {
                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    coinDetailFlow.collect { result ->
                        when (result) {
                            is Resource.Loading -> progressBar.visible()
                            is Resource.Success -> {
                                progressBar.gone()
                                model = result.data
                                currentPrice = result.data.currentPrice
                            }

                            is Resource.Error -> {
                                progressBar.gone()
                                requireView().showSnackBar(result.throwable.message.toString())
                            }
                        }
                    }
                }

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    addToFavourite.collect { result ->
                        when (result) {
                            is DetailUIEffect.SnackBar -> requireView().showSnackBar(result.message)
                        }
                    }
                }

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    currentPriceFlow.collect {
                        currentPrice = it
                    }
                }
            }
        }
    }
}