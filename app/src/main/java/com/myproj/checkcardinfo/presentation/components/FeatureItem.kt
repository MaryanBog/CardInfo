package com.myproj.checkcardinfo.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.myproj.checkcardinfo.R
import com.myproj.checkcardinfo.data.models.CardInfo
import com.myproj.checkcardinfo.presentation.menu.MenuViewModel
import com.myproj.checkcardinfo.ui.lightPalette
import com.myproj.checkcardinfo.util.Constant

@Composable
fun FeatureItem(
    cardInfo: CardInfo,
    menuViewModel: MenuViewModel,
    navController: NavController
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(lightPalette.blueViolet1)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            CardHeadInfoSection(cardInfo)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { }
                    .background(
                        color = lightPalette.buttonBlue,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(vertical = 4.dp, horizontal = 4.dp)
            ) {
                RowTextDescription(
                    cardInfoRes = cardInfo.country.latitude,
                    textLabel = R.string.country_latitude
                )
                RowTextDescription(
                    cardInfoRes = cardInfo.country.longitude,
                    textLabel = R.string.country_longitude
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            CardWebSection(cardInfo, menuViewModel, navController)
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { }
                    .background(
                        color = lightPalette.lightGreen1,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(vertical = 4.dp, horizontal = 4.dp),
            ) {
                RowTextDescription(
                    cardInfoRes = cardInfo.bank.phone,
                    textLabel = R.string.bank_phone
                )
            }
            RowTextDescription(
                cardInfoRes = cardInfo.bank.city,
                textLabel = R.string.bank_city
            )
        }
    }
}

@Composable
private fun CardWebSection(
    cardInfo: CardInfo,
    menuViewModel: MenuViewModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (cardInfo.bank.urlString == null) {
                    menuViewModel.onErrorShow(
                        stringRes = R.string.no_bank_site
                    )
                } else {
                    navController.navigate(
                        R.id.webFragment,
                        args = bundleOf(
                            Constant.URL_BANK to cardInfo.bank.urlString
                        )
                    )
                }
            }
            .background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(vertical = 4.dp, horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.bank_name),
            style = MaterialTheme.typography.subtitle2,
            lineHeight = 16.sp
        )
        Text(
            text = cardInfo.bank.name ?: "No information",
            style = MaterialTheme.typography.subtitle2,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.bank_url),
            style = MaterialTheme.typography.subtitle2,
            lineHeight = 16.sp
        )
        Text(
            text = cardInfo.bank.urlString ?: "No information",
            style = MaterialTheme.typography.subtitle2,
            lineHeight = 20.sp
        )
    }
}

@Composable
fun CardHeadInfoSection(cardInfo: CardInfo) {
    RowTextDescription(
        cardInfoRes = cardInfo.number.length,
        textLabel = R.string.card_number_length
    )
    RowTextDescription(
        cardInfoRes = cardInfo.number.availability,
        textLabel = R.string.card_number_availability,
    )
    RowTextDescription(
        cardInfoRes = cardInfo.scheme,
        textLabel = R.string.card_scheme
    )
    RowTextDescription(
        cardInfoRes = cardInfo.type,
        textLabel = R.string.card_type
    )
    RowTextDescription(
        cardInfoRes = cardInfo.brand,
        textLabel = R.string.card_brand
    )
    RowTextDescription(
        cardInfoRes = cardInfo.prepaid,
        textLabel = R.string.card_prepaid
    )
    RowTextDescription(
        cardInfoRes = cardInfo.country.numeric,
        textLabel = R.string.country_numeric
    )
    RowTextDescription(
        cardInfoRes = cardInfo.country.name,
        textLabel = R.string.country_name
    )
}