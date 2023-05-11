package com.tunahanozatac.cryptoapps.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahanozatac.cryptoapps.R
import com.tunahanozatac.cryptoapps.util.DetailUIEffect
import com.tunahanozatac.cryptoapps.util.Resource
import com.tunahanozatac.cryptoapps.domain.model.CoinDetailUI
import com.tunahanozatac.cryptoapps.domain.provider.StringResourceProvider
import com.tunahanozatac.cryptoapps.domain.usecase.coins.GetCoinByIdUseCase
import com.tunahanozatac.cryptoapps.domain.usecase.coins.GetCurrentPriceByIdUseCase
import com.tunahanozatac.cryptoapps.domain.usecase.favourites.AddToFavouritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration

@HiltViewModel
class CryptoDetailsViewModel @Inject constructor(
    private val getCoinByIdUseCase: GetCoinByIdUseCase,
    private val currentPriceByIdUseCase: GetCurrentPriceByIdUseCase,
    private val addToFavouritesUseCase: AddToFavouritesUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val stringResourceProvider: StringResourceProvider
) : ViewModel() {

    private var coinDetailUI: CoinDetailUI? = null

    private val _coinDetailFlow = MutableStateFlow<Resource<CoinDetailUI>>(Resource.Loading)
    val coinDetailFlow = _coinDetailFlow.asStateFlow()

    private val _currentPriceFlow = MutableStateFlow(0.0)
    val currentPriceFlow = _currentPriceFlow.asStateFlow()

    private val _addToFavourite = MutableSharedFlow<DetailUIEffect>()
    val addToFavourite = _addToFavourite.asSharedFlow()

    init {
//        savedStateHandle.get<String>(COIN_ID)?.let {
//            coinById(it)
//        }
    }

    fun coinById(coinId: String) = viewModelScope.launch {
        getCoinByIdUseCase.invoke(coinId).collect { result ->
            when (result) {
                is Resource.Success -> {
                    coinDetailUI = result.data
                    _coinDetailFlow.emit(Resource.Success(result.data))
                }
                is Resource.Loading -> _coinDetailFlow.emit(Resource.Loading)
                is Resource.Error -> _coinDetailFlow.emit(Resource.Error(result.throwable))
            }
        }
    }

    fun currentPriceById(period: Duration,coinId: String) = viewModelScope.launch {
        coinId.let {
            currentPriceByIdUseCase.invoke(period, it).collect { result ->
                when (result) {
                    Resource.Loading -> {}
                    is Resource.Success -> _currentPriceFlow.emit(result.data)
                    is Resource.Error -> {}
                }
            }
        }
    }

    fun addToFavourites() = viewModelScope.launch {
        coinDetailUI?.let {
            addToFavouritesUseCase.invoke(it).collect { result ->
                when (result) {
                    Resource.Loading -> {}
                    is Resource.Success -> _addToFavourite.emit(
                        DetailUIEffect.SnackBar(
                            stringResourceProvider.getString(R.string.favourite_add_success)
                        )
                    )
                    is Resource.Error -> _addToFavourite.emit(
                        DetailUIEffect.SnackBar(
                            result.throwable.message.toString()
                        )
                    )
                }
            }
        } ?: run {
            _addToFavourite.emit(
                DetailUIEffect.SnackBar(stringResourceProvider.getString(com.google.android.material.R.string.error_a11y_label))
            )
        }
    }
}