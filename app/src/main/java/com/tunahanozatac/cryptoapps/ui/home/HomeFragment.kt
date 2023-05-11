package com.tunahanozatac.cryptoapps.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.work.WorkInfo
import com.tunahanozatac.cryptoapps.core.BaseFragment
import com.tunahanozatac.cryptoapps.databinding.FragmentHomeBinding
import com.tunahanozatac.cryptoapps.util.Resource
import com.tunahanozatac.cryptoapps.util.getQueryTextChangeStateFlow
import com.tunahanozatac.cryptoapps.util.gone
import com.tunahanozatac.cryptoapps.util.showSnackBar
import com.tunahanozatac.cryptoapps.util.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()
    private val homeAdapter by lazy { HomeAdapter() }

    override fun layoutResource(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun viewCreated() {
        initUI()
        collectData()
        setEditTextSearchProperties()
    }

    private fun setEditTextSearchProperties() {
        binding?.apply {
            lifecycleScope.launch {
                searchEditText.addTextChangedListener {
                    viewModel.searchCoin(it.toString())
                }
            }
        }
    }

    private fun initUI() {
        binding?.apply {
            recyclerview.adapter = homeAdapter
        }
    }

    private fun collectData() {
        binding?.apply {
            with(viewModel) {
                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    currentUser.collect { result ->
                        when (result) {
                            is Resource.Loading -> loadingInc.progressBar2.visible()
                            is Resource.Success -> {
                                loadingInc.progressBar2.gone()
                            }

                            is Resource.Error -> {
                                loadingInc.progressBar2.gone()
                                requireView().showSnackBar(result.throwable.message.toString())
                            }
                        }
                    }

                    coinMarketsFlow.collect { result ->
                        when (result) {
                            is Resource.Loading -> loadingInc.progressBar2.visible()
                            is Resource.Success -> {
                                loadingInc.progressBar2.gone()
                                homeAdapter.submitList(result.data)
                            }

                            is Resource.Error -> {
                                loadingInc.progressBar2.gone()
                                requireView().showSnackBar(result.throwable.message.toString())
                            }
                        }
                    }
                }

                workInfo.observe(viewLifecycleOwner) { listOfWorkInfo ->
                    if (listOfWorkInfo == null || listOfWorkInfo.isEmpty()) {
                        return@observe
                    }
                    val workInfo: WorkInfo = listOfWorkInfo[0]
                    if (workInfo.state == WorkInfo.State.ENQUEUED) {
                        coinMarkets()
                    }
                }

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    binding?.apply {
                        coinListFlow.collect { result ->
                            when (result) {
                                is Resource.Loading -> loadingInc.progressBar2.visible()
                                is Resource.Success -> {
                                    loadingInc.progressBar2.gone()
                                    Log.e("Resource.Success", result.data.size.toString())
                                }

                                is Resource.Error -> {
                                    loadingInc.progressBar2.gone()
                                    requireView().showSnackBar(result.throwable.message.toString())
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}