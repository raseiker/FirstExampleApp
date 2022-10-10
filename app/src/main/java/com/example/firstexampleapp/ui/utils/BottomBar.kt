package com.example.firstexampleapp.ui.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.firstexampleapp.ui.theme.FirstExampleAppTheme
import com.example.firstexampleapp.R
import com.example.firstexampleapp.ui.navigation.Screen

//@Preview(showBackground = true, device = Devices.NEXUS_5X)
//@Composable
//fun MyBottomBarPreview() {
//    FirstExampleAppTheme() {
//        Scaffold(
//            bottomBar = {
//                MyBottomBar(
//                    isSelected = listOf(false, false, false)
//                )
//            }
//        ) {
//
//        }
//    }
//}

@Composable
fun MyBottomBar(
    isSelected: List<Boolean>,
    modifier: Modifier = Modifier,
    onItemBottomBarClicked: (String) -> Unit = {}
) {
    BottomNavigation(
        modifier = modifier
    ) {
        BottomNavigationItem(
            selected = isSelected[0],
            onClick = { onItemBottomBarClicked(Screen.HomeScreen.route) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "inicio"
                )
            },
            label = { Text(text = "Hoy") },
            selectedContentColor = MaterialTheme.colors.secondaryVariant,
            unselectedContentColor = MaterialTheme.colors.onPrimary.copy(alpha = ContentAlpha.medium)
        )

        BottomNavigationItem(
            selected = isSelected[1],
            onClick = { onItemBottomBarClicked(Screen.ExploreScreen.route)},
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_library_books_24),
                    contentDescription = "Explorar"
                )
            },
            label = { Text(text = "Explorar") },
            selectedContentColor = MaterialTheme.colors.secondaryVariant,
            unselectedContentColor = MaterialTheme.colors.onPrimary.copy(alpha = ContentAlpha.medium)
        )

        BottomNavigationItem(
            selected = isSelected[2],
            onClick = { onItemBottomBarClicked(Screen.ModuleScreen.route) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_dashboard_24),
                    contentDescription = "Módulos"
                )
            },
            label = { Text(text = "Módulos") },
            selectedContentColor = MaterialTheme.colors.secondaryVariant,
            unselectedContentColor = MaterialTheme.colors.onPrimary.copy(alpha = ContentAlpha.medium)
        )
    }
}