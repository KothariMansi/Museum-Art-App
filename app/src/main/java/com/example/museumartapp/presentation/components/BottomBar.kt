package com.example.museumartapp.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.museumartapp.Destinations
import com.example.museumartapp.R
import com.example.museumartapp.ui.theme.MuseumArtAppTheme

@Composable
fun BottomBar(
    navController: NavController,
    selected: Int
) {
    BottomAppBar {
        NavigationBarItem(
            selected = selected == 0,
            onClick = {
                navController.navigate(Destinations.HOME.name)
            },
            icon = {
                Icon(imageVector = Icons.Default.Home, contentDescription = "")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = colorResource(id = R.color.md_theme_secondary),
                indicatorColor = colorResource(id = R.color.md_theme_onSecondary),
                unselectedIconColor = colorResource(id = R.color.md_theme_secondary),
            )
        )
        NavigationBarItem(
            selected = selected == 1,
            onClick = {
                navController.navigate(Destinations.BOOKMARK.name)
            },
            icon = {
                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = colorResource(id = R.color.md_theme_secondary),
                indicatorColor = colorResource(id = R.color.md_theme_onSecondary),
                unselectedIconColor = colorResource(id = R.color.md_theme_secondary),
            )
        )
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    MuseumArtAppTheme {
        BottomBar(
            rememberNavController(),
            0
        )
    }
}