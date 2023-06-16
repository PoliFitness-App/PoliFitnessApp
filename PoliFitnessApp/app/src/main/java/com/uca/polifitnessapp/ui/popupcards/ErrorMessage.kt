package com.uca.polifitnessapp.ui.popupcards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uca.polifitnessapp.R

@Preview
@Composable 
fun ErrorMessageCard() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(22.dp))
            .background(
                colorResource(id = R.color.white)
            )
            .padding(10.dp)
            .width(375.dp)
            .height(578.dp)
    ) {

    }
}
