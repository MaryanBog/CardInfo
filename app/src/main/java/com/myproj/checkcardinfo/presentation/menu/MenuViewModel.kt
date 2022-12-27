package com.myproj.checkcardinfo.presentation.menu

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproj.checkcardinfo.R
import com.myproj.checkcardinfo.data.CardRepository
import com.myproj.checkcardinfo.data.db.models.CardEntity
import com.myproj.checkcardinfo.data.models.CardInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private var repository: CardRepository
): ViewModel() {

    private var _enteredCardNumber = MutableStateFlow<String?>(null)
    val enteredCardNumber = _enteredCardNumber.asStateFlow()

    private var _errorMessage = MutableStateFlow<Int?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    private var _menuState = MutableStateFlow<MenuState>(MenuState.Start)
    val menuState = _menuState.asStateFlow()

    private var _cardInfo = MutableStateFlow<CardInfo?>(null)
    val cardInfo = _cardInfo.asStateFlow()

    private var _listCardInfo = MutableStateFlow<List<CardInfo>>(emptyList())
    val listCardInfo = _listCardInfo.asStateFlow()

    fun getAllCard() {
        viewModelScope.launch(Dispatchers.Default) {
            _listCardInfo.emit(repository.getAllCard().map { it.toDomain() })
        }
    }

    fun onCheckCardNumber(enteredCardNumber: String?){
        viewModelScope.launch {
            _enteredCardNumber.emit(enteredCardNumber)
            _menuState.value = MenuState.Request
        }
    }

    fun onErrorShow(@StringRes stringRes: Int?) {
        viewModelScope.launch {
            if (stringRes == null) {
                _errorMessage.emit(null)
            } else {
                _errorMessage.emit(stringRes)
            }
        }
    }

    fun getCardInfo(cardInfo: CardInfo){
        viewModelScope.launch {
            _cardInfo.emit(cardInfo)
        }
    }

    fun loadCardInfo(cardNumber: Int){
        viewModelScope.launch {
            kotlin.runCatching {
                repository.loadCardInfo(cardNumber)
            }.fold(
                onSuccess = {
                    _menuState.value = MenuState.Success(it)
                    repository.saveCard(
                        cardData = CardEntity.fromDomain(it)
                    )
                    Log.d("MenuViewModel", "load card success")
                },
                onFailure = {
                    Log.d("MenuViewModel", "load card failure")
                    _menuState.value = MenuState.Error(
                        R.string.response_received_error
                    )
                }
            )
        }
    }
}