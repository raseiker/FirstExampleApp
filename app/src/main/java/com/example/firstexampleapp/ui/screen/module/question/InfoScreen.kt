package com.example.firstexampleapp.ui.screen.module.question

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.firstexampleapp.ui.utils.MyTopApBar

//@Preview(showBackground = true, device = Devices.DEFAULT)
//@Composable
//fun MyPreview(){
//    UpdateQuestionScreen()
//}


@Composable
fun InfoScreen(
    @DrawableRes imageInfo: Int,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            MyTopApBar(
                title = "Info",
                navIcon = Icons.Default.ArrowBack,
                onNavigateBack = onNavigateBack
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(paddingValues = it)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = imageInfo),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
    }
}