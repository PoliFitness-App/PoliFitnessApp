package com.uca.polifitnessapp.ui.navigation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.uca.polifitnessapp.R

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onBackPress : () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        // center items horizontally in the row
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(
            onClick = { onBackPress() }
        ) {
            Icon(
                Icons.Outlined.ArrowBack,
                contentDescription = "Back button"
            )
        }
        Text(
            text = stringResource(R.string.back_label),
            style = MaterialTheme.typography.titleSmall
        )
    }
}