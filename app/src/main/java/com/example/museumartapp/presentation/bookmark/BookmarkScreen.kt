package com.example.museumartapp.presentation.bookmark

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.museumartapp.presentation.components.ArtDetail


@Composable
fun BookmarkScreen(
    modifier: Modifier
) {
    val bookMarkViewModel: BookMarkViewModel = hiltViewModel()
    val state by bookMarkViewModel.uiState.collectAsState()
    val artList = state.artList.collectAsState(initial = emptyList()).value
    if (artList.isEmpty()) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "No saved Item")
        }
    }
    else {
        LazyColumn(
            modifier = modifier
        ) {
            items(artList) {
                ArtDetail(
                    record = it,
                    selected = true
                ) { bookMarkViewModel.deleteArt(it) }
            }
        }
    }

}
