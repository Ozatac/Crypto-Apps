package com.tunahanozatac.cryptoapps.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahanozatac.cryptoapps.domain.model.CoinMarketsUI
import com.tunahanozatac.cryptoapps.domain.usecase.coins.GetCoinMarketsUseCase
import com.tunahanozatac.cryptoapps.domain.usecase.coins.SearchCoinUseCase
import com.tunahanozatac.cryptoapps.domain.usecase.main.GetFirebaseUserUseCase
import com.tunahanozatac.cryptoapps.domain.usecase.worker.WorkerOnSuccessUseCase
import com.tunahanozatac.cryptoapps.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getFirebaseUserUseCase: GetFirebaseUserUseCase,
    private val getCoinMarketsUseCase: GetCoinMarketsUseCase,
    workerOnSuccessUseCase: WorkerOnSuccessUseCase,
    private val searchCoinUseCase: SearchCoinUseCase,
) : ViewModel() {

    private val _coinMarketsFlow = MutableStateFlow<Resource<List<CoinMarketsUI>>>(Resource.Loading)
    val coinMarketsFlow = _coinMarketsFlow.asStateFlow()

    private val _coinListFlow = MutableStateFlow<Resource<List<CoinMarketsUI>>>(Resource.Loading)
    val coinListFlow = _coinListFlow.asStateFlow()

    init {
        coinMarkets()
    }

    val workInfo = workerOnSuccessUseCase.invoke()

    val currentUser = getFirebaseUserUseCase.invoke()

    fun coinMarkets() = viewModelScope.launch {
        getCoinMarketsUseCase.invoke().collect { result ->
            _coinMarketsFlow.emit(result)
        }
    }

    fun searchCoin(searchQuery: String) = viewModelScope.launch {
        searchCoinUseCase.invoke(searchQuery).collect {
            _coinListFlow.emit(it)
        }
    }
}