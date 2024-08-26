package com.example.museumartapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.museumartapp.presentation.components.ArtDetail
import com.example.museumartapp.presentation.components.TopBar

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val artMuseumData by homeViewModel.recordData.observeAsState(emptyList())

    if (artMuseumData.isNotEmpty()) {
        Scaffold(
            topBar = { TopBar() }
        ) {
            LazyColumn(
                modifier = Modifier.padding(it).padding(top=4.dp)
            ) {
                items(artMuseumData) {record ->
                    ArtDetail(record = record)
                }
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Unable to fetch Data"
            )
        }
    }
}