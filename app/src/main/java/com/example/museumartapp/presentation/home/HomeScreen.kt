package com.example.museumartapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.museumartapp.presentation.components.ArtDetail

@Composable
fun HomeScreen(
    modifier: Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val artMuseumData by homeViewModel.recordData.observeAsState(emptyList())


    if (artMuseumData.isNotEmpty()) {
        LazyColumn(
            modifier = modifier
                .padding(top = 4.dp)
        ) {
            items(artMuseumData) { record ->
                val selected = rememberSaveable { mutableStateOf(false) }
                ArtDetail(
                    record = record,
                    selected = selected.value
                ) {
                    selected.value = !selected.value
                    homeViewModel.addArt(record)
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