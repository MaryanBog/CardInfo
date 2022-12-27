package com.myproj.checkcardinfo.presentation.menu

import com.myproj.checkcardinfo.data.models.CardInfo

sealed class MenuState{
    object Start: MenuState()
    object Request: MenuState()
    class Error(val errorResources: Int): MenuState()
    class Success(val cardInfo: CardInfo): MenuState()
}
