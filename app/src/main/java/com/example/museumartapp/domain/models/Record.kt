package com.example.museumartapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Info(
    val totalrecordsperquery: Int,
    val totalrecords: Int,
    val pages: Int,
    val page: Int,
    val next: String?,
    val responsetime: String
)

@Entity
data class Record(
    @PrimaryKey
    val id: Int,
    val baseimageurl: String?,
    val date: String?,
    val technique: String?
)