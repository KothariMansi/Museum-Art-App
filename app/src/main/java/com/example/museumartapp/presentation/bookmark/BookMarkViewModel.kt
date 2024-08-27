package com.example.museumartapp.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museumartapp.data.local.Dao
import com.example.museumartapp.domain.models.Record
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val dao: Dao
): ViewModel() {
    private val _uiState = MutableStateFlow(BookmarkState())
    var uiState: StateFlow<BookmarkState> = _uiState.asStateFlow()

    private val artList = dao.getArt()
    init {
        getArt(artList)
    }

    private fun getArt(artList: Flow<List<Record>>) {
        _uiState.update {
            it.copy(
                artList = artList
            )
        }
    }

    fun deleteArt(artMuseum: Record){
        viewModelScope.launch {
            dao.deleteArt(record = artMuseum)
        }
    }
}

data class BookmarkState(
    val artList: Flow<List<Record>> = flowOf()
)