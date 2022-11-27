package com.example.firstexampleapp.ui.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.DefaultTintColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.R

//@Preview(showBackground = true, device = Devices.NEXUS_5X)
//@Composable
//fun MyTopBarPreview() {
//    FirstExampleAppTheme() {
//        Scaffold(
//            topBar = {
//                MyTopApBar(
//                    title = "FAQs",
//                    navIcon = Icons.Default.ArrowBack,
//                    actionIcon = Icons.Default.Info,
//                    modifier = Modifier
//                )
//            }
//        ) {
//            Column(modifier = Modifier.padding(paddingValues = it)) {
//
//            }
//        }
//    }
//}

@Composable
fun MyTopApBar(
    title: String? = null,
    navIcon: ImageVector,
    actionIconBookMark: ImageVector? = null,
    actionIcon: ImageVector? = null,
    isFavorite: Boolean = false,
    onNavigateBack: () -> Unit,
    onInfoClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var isFav by remember {//change color
        mutableStateOf(isFavorite)
    }
    TopAppBar(
        title = { title?.let { Text(text = it) } },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(imageVector = navIcon, contentDescription = "go back")
            }
        },
        actions = {
            actionIcon?.let {
                IconButton(onClick = onInfoClicked) {
                    Icon(
                        imageVector = it,
                        contentDescription = "info"
                    )

                }
            }
            actionIconBookMark?.let {
                IconButton(onClick = { isFav = !isFav}) {
                    Icon(
                        imageVector = it,
                        contentDescription = "bookmark",
                        tint = if (isFav) MaterialTheme.colors.secondaryVariant else LocalContentColor.current.copy(
                            alpha = LocalContentAlpha.current
                        )
                    )
                }
            }
        },
        modifier = modifier
    )
}