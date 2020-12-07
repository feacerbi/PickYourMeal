package com.felipeacerbi.pickyourmeal.viewstate

import com.felipeacerbi.pickyourmeal.repository.model.Recipe
import java.util.*

data class RecipesViewState(
    val recipes: List<Recipe> = listOf(),
    val loading: Boolean = false,
    val currentDate: Calendar = Calendar.getInstance(),
    val showEmpty: Boolean = false
) : ViewState