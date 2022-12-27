package com.myproj.checkcardinfo.presentation.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myproj.checkcardinfo.R
import com.myproj.checkcardinfo.presentation.components.FeatureItem
import com.myproj.checkcardinfo.presentation.menu.MenuViewModel

@Composable
fun BaseScreen(
    menuViewModel: MenuViewModel,
    navController: NavController
) {
    menuViewModel.getAllCard()
    val listCardInfo = menuViewModel.listCardInfo.collectAsState().value

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        androidx.compose.material.Text(
            text = stringResource(R.string.full_card_info),
            style = TextStyle(color = Color.White, fontSize = 16.sp),
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
        LazyColumn(
            contentPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(listCardInfo.size) {
                FeatureItem(
                    cardInfo = listCardInfo[it],
                    menuViewModel = menuViewModel,
                    navController = navController
                )
            }
        }
    }
}