package com.felipeacerbi.pickyourmeal.repository.model

data class Recipe(
    val id: String,
    val title: String,
    val headline: String,
    val picture: String,
    val tags: List<String> = listOf()
)