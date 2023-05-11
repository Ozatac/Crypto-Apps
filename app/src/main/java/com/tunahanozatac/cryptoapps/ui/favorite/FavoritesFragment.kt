package com.tunahanozatac.cryptoapps.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.tunahanozatac.cryptoapps.core.BaseFragment
import com.tunahanozatac.cryptoapps.util.DetailUIEffect
import com.tunahanozatac.cryptoapps.util.Resource
import com.tunahanozatac.cryptoapps.util.gone
import com.tunahanozatac.cryptoapps.util.showSnackBar
import com.tunahanozatac.cryptoapps.util.visible
import com.tunahanozatac.cryptoapps.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>() {

    override val viewModel: FavoritesViewModel by viewModels()
    private val favoritesAdapter by lazy { FavoritesAdapter() }

    override fun layoutResource(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentFavoritesBinding.inflate(inflater, container, false)

    override fun viewCreated() {
        initUI()
        collectData()
    }

    private fun initUI() {
        binding?.apply {
            rvFavourites.adapter = favoritesAdapter
            favoritesAdapter.onDeleteClick = { viewModel.deleteFromFavourites(it) }
        }
    }

    private fun collectData() {
        binding?.apply {
            with(viewModel) {
                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    favouritesFlow.collect { result ->
                        when (result) {
                            is Resource.Loading -> progressBar.visible()
                            is Resource.Success -> {
                                progressBar.gone()
                                favoritesAdapter.submitList(result.data)
                            }
                            is Resource.Error -> {
                                progressBar.gone()
                            }
                        }
                    }
                }

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    deleteFromFavouritesFlow.collect {
                        when (it) {
                            is DetailUIEffect.SnackBar -> requireView().showSnackBar(it.message)
                        }
                    }
                }
            }
        }
    }
}