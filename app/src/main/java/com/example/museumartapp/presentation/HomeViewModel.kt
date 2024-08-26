package com.example.museumartapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museumartapp.domain.models.Record
import com.example.museumartapp.repository.ArtRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val artRepository: ArtRepository
): ViewModel() {

    init {
        artMuseum()
    }

    private var _recordData = MutableLiveData<List<Record>>()
    val recordData : LiveData<List<Record>> get()  = _recordData
     private fun artMuseum() {
         viewModelScope.launch {

             try {
                 val response = withContext(Dispatchers.IO) {
                     artRepository.getArtData().execute()
                 }
                 if (response.isSuccessful) {
                     response.body()?.records?.let { recordList ->
                         val recordList = recordList.map {
                             Record(
                                 it.id, it.baseimageurl, it.date, it.technique
                             )
                         }
                         _recordData.postValue(recordList)
                     }
                 } else {
                     Log.e("Error", "Failed with error: ${response.code()} - ${response.message()}")
                 }
             }
             catch (e: Exception) {
                 Log.e("Error", "Failed with error: ${e.message}")
                 e.printStackTrace()
             }
         }
     }

}