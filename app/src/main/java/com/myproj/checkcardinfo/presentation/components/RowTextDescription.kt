package com.myproj.checkcardinfo.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

@Composable
fun RowTextDescription(
    cardInfoRes: Any?,
    @StringRes textLabel: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = textLabel),
            style = MaterialTheme.typography.subtitle2,
            lineHeight = 16.sp
        )
        Text(
            text = cardInfoRes?.toString() ?: "No information",
            style = MaterialTheme.typography.subtitle2,
            lineHeight = 20.sp,
        )
    }
}