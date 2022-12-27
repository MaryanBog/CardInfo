package com.myproj.checkcardinfo.presentation.menu

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myproj.checkcardinfo.R
import com.myproj.checkcardinfo.presentation.components.CurrentCardNumberSection
import com.myproj.checkcardinfo.presentation.components.FeatureItem
import com.myproj.checkcardinfo.ui.lightPalette
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    navController: NavController,
    menuViewModel: MenuViewModel
) {
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val cardInfo = menuViewModel.cardInfo.collectAsState().value
    val errorMessage = menuViewModel.errorMessage.collectAsState().value
    val stringError = errorMessage?.let { stringResource(id = it) }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState
            )
        }
    ) {
        LaunchedEffect(stringError) {
            if (stringError != null) {
                menuViewModel.onErrorShow(null)
                scope.launch {
                    snackBarHostState.showSnackbar(
                        stringError
                    )
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = lightPalette.deepBlue)
        ) {
            HeadTextSection()
            CurrentCardNumberSection(
                menuViewModel = menuViewModel
            )
            Text(
                text = stringResource(R.string.full_card_info),
                style =  TextStyle(color = Color.White, fontSize = 16.sp),
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
            if (cardInfo != null){
                FeatureItem(
                    cardInfo = cardInfo,
                    menuViewModel = menuViewModel,
                    navController = navController
                )
            }
            BottomSection(navController = navController)
        }
    }
}

@Composable
fun BottomSection(
    navController: NavController
) {
    Spacer(modifier = Modifier.height(24.dp))
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(
                color = lightPalette.lightRed,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {
                navController.navigate(R.id.action_menuFragment_to_baseFragment)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.view_all_requests),
            style =  TextStyle(color = Color.White, fontSize = 16.sp),
            modifier = Modifier
                .padding(8.dp),
            textAlign = TextAlign.Center,
        )
    }

}

@Composable
fun HeadTextSection() {
    Text(
        text = stringResource(R.string.enter_first_eight),
        style = MaterialTheme.typography.body1,
        color = lightPalette.textWhite,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        textAlign = TextAlign.Center
    )
}