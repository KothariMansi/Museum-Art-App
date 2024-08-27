package com.example.museumartapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.museumartapp.presentation.bookmark.BookmarkScreen
import com.example.museumartapp.presentation.home.HomeScreen
import com.example.museumartapp.presentation.components.BottomBar
import com.example.museumartapp.presentation.components.TopBar
import com.example.museumartapp.ui.theme.MuseumArtAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuseumArtAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MuseumArtApp()
                }
            }
        }
    }
}

enum class Destinations{
    HOME, BOOKMARK
}
@Composable
fun MuseumArtApp() {
    val navController = rememberNavController()

    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Destinations.HOME.name -> 0
            Destinations.BOOKMARK.name -> 1
            else -> 0
        }
    }
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomBar(navController = navController, selected = selectedItem)}
    ) {paddingValues->
        NavHost(navController = navController, startDestination = Destinations.HOME.name) {
            composable(Destinations.HOME.name) {
                HomeScreen(modifier = Modifier.padding(paddingValues))
            }
            composable(Destinations.BOOKMARK.name) {
                BookmarkScreen(modifier = Modifier.padding(paddingValues))
            }
        }
    }
}
