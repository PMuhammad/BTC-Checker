package com.example.btcchecker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.btcchecker.repo.CryptoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(private val repo: CryptoRepo): ViewModel() {

    private val _btcPrice = MutableStateFlow(repo.btcPrice)
    val btcPrice: StateFlow<Int> = _btcPrice

    private val _btcChange = MutableStateFlow(repo.btcChange)
    val btcChange: StateFlow<Double> = _btcChange

    fun updateData(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _btcPrice.value = repo.getBtcPrice().toInt()
                _btcChange.value = repo.getBtcChange()
            }catch (e: Exception){
                _btcPrice.value = -1000
                _btcChange.value = -10.0
            }
        }
    }

}