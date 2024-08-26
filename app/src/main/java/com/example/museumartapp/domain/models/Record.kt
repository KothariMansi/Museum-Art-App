package com.example.museumartapp.domain.models

data class Info(
    val totalrecordsperquery: Int,
    val totalrecords: Int,
    val pages: Int,
    val page: Int,
    val next: String?,
    val responsetime: String
)

data class Record(
    val id: Int,
    val baseimageurl: String?,
    val date: String?,
    val technique: String?
)